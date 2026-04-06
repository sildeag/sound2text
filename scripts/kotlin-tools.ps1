param(
    [Parameter(Mandatory = $true)]
    [ValidateSet("Tree", "Mermaid", "Diff")]
    [string]$Mode,

    [string]$Old,
    [string]$New,

    [bool]$ShowFullPackagePath = $false
)

# --- Configuration ---------------------------------------------------------

$basePath = "D:\Android\Projects\sound2text"
$modules  = @(
    "ui-android",
    "stt-android",
    "config",
    "core",
    "ui-common",
    "ui-desktop",
    "ui-legacy",
    "stt-desktop",
    "pdf-desktop"
)

$sourceSets = @("androidMain", "commonMain", "jvmMain")

# --- Shared helpers --------------------------------------------------------

function Get-KotlinTree {
    param(
        [string]$Path,
        [int]$Indent = 0,
        [bool]$ShowFullPackagePath = $false
    )

    $output = ""
    $prefix = "  " * $Indent

    if ($ShowFullPackagePath -and $Path -match "com\\sildeag\\sound2text") {
        $start = $Path.IndexOf("com")
        $folderLabel = $Path.Substring($start).Replace("\", "/")
    } else {
        $folderLabel = Split-Path $Path -Leaf
    }

    $output += "$prefix$folderLabel/`n"

    Get-ChildItem $Path -File -Filter *.kt | Sort-Object Name | ForEach-Object {
        $output += "$prefix  $($_.Name)`n"
    }

    Get-ChildItem $Path -Directory | Sort-Object Name | ForEach-Object {
        $output += Get-KotlinTree -Path $_.FullName -Indent ($Indent + 1) -ShowFullPackagePath $ShowFullPackagePath
    }

    return $output
}

function Get-MermaidTree {
    param(
        [string]$Path,
        [string]$ParentId
    )

    $output = ""
    $folderName = Split-Path $Path -Leaf
    $currentId = "id_" + ($Path.GetHashCode() -replace '-', 'n')

    $output += "    $ParentId --> $currentId[`"$folderName/`"]`n"

    Get-ChildItem $Path -File -Filter *.kt | Sort-Object Name | ForEach-Object {
        $fileId = "id_" + ($_.FullName.GetHashCode() -replace '-', 'n')
        $output += "    $currentId --> $fileId[`"$($_.Name)`"]`n"
    }

    Get-ChildItem $Path -Directory | Sort-Object Name | ForEach-Object {
        $output += Get-MermaidTree -Path $_.FullName -ParentId $currentId
    }

    return $output
}

function Compare-KotlinTrees {
    param(
        [string]$OldFile,
        [string]$NewFile
    )

    if (-not (Test-Path $OldFile)) {
        Write-Host "Old file not found: $OldFile" -ForegroundColor Red
        return
    }
    if (-not (Test-Path $NewFile)) {
        Write-Host "New file not found: $NewFile" -ForegroundColor Red
        return
    }

    $old = Get-Content $OldFile
    $new = Get-Content $NewFile

    $diff = Compare-Object -ReferenceObject $old -DifferenceObject $new -IncludeEqual:$false

    foreach ($d in $diff) {
        if ($d.SideIndicator -eq "<=") {
            Write-Host "- $($d.InputObject)" -ForegroundColor Red
        } elseif ($d.SideIndicator -eq "=>") {
            Write-Host "+ $($d.InputObject)" -ForegroundColor Green
        }
    }
}

# --- Mode: Tree -----------------------------------------------------------

if ($Mode -eq "Tree") {
    $treeOutput = "sound2text/`n"

    foreach ($mod in $modules) {
        $treeOutput += "  $mod/`n"

        foreach ($src in $sourceSets) {
            $srcPath = Join-Path $basePath $mod |
                    Join-Path -ChildPath "src\$src\kotlin\com\sildeag\sound2text"

            if (Test-Path $srcPath) {
                $treeOutput += "    $src/`n"
                $treeOutput += Get-KotlinTree -Path $srcPath -Indent 3 -ShowFullPackagePath $ShowFullPackagePath
            }
        }
    }

    Set-Clipboard $treeOutput
    Write-Host "Kotlin tree copied to clipboard."
    return
}

# --- Mode: Mermaid --------------------------------------------------------

if ($Mode -eq "Mermaid") {
    $mermaid = "flowchart TB`n"
    $mermaid += "  root((sound2text))`n"

    foreach ($mod in $modules) {
        $modId = "mod_" + ($mod -replace '-', '_')
        $mermaid += "  subgraph $modId [`"$mod`"]`n"
        $mermaid += "    root --> $modId`n"

        foreach ($src in $sourceSets) {
            $srcPath = Join-Path $basePath $mod |
                    Join-Path -ChildPath "src\$src\kotlin\com\sildeag\sound2text"

            if (Test-Path $srcPath) {
                $srcId = "${modId}_${src}"
                $mermaid += "    subgraph $srcId [`"$src`"]`n"
                $mermaid += Get-MermaidTree -Path $srcPath -ParentId $srcId
                $mermaid += "    end`n"
            }
        }

        $mermaid += "  end`n"
    }

    Set-Clipboard $mermaid
    Write-Host "Mermaid Kotlin tree copied to clipboard."
    return
}

# --- Mode: Diff -----------------------------------------------------------

if ($Mode -eq "Diff") {
    if (-not $Old -or -not $New) {
        Write-Host "For Diff mode, please provide -Old and -New file paths." -ForegroundColor Yellow
        return
    }

    Compare-KotlinTrees -OldFile $Old -NewFile $New
    return
}
