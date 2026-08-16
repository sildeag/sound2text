param(
    [string[]]$Modules,
    [string]$BasePath = "D:\Android\Projects\sound2text",
    [bool]$ShowFullPackagePath = $false,
    [bool]$ShowCode = $false
)

# Default modules if none provided
#if (-not $PSBoundParameters.ContainsKey("Modules")) {
#$Modules = @(
if (-not $PSBoundParameters.ContainsKey("Modules") -or $Modules.Count -eq 0) {
    $Modules = @(
        ":appcommon",
        ":core",
        ":di",
        ":feature-capabilities",
        ":feature-form",
        ":feature-genealogy",
        ":feature-history",
        ":feature-pdf",
        ":feature-pdfwizard",
        ":feature-recording",
        ":feature-settings",
        ":feature-sqlite",
        ":feature-stt",
        ":pdf-android",
        ":pdf-desktop",
        ":platform",
        ":settings",
        ":storage",
        ":stt-desktop",
        ":stt-android",
        ":ui-android",
        ":ui-common",
        ":ui-desktop",
        ":test-harness",
        ":ui-legacy"
    )
}

# Source sets to scan
$sourceSets = @("commonMain", "androidMain", "jvmMain", "desktopMain")

function Get-PackageFromFile {
    param([string]$FilePath)

    $content = Get-Content $FilePath -ErrorAction SilentlyContinue
    foreach ($line in $content) {
        if ($line -match "^package\s+(.+)$") {
            return $Matches[1]
        }
    }
    return "<no package>"
}

function Get-KtModuleName {
    param([string]$Package)

    if ($Package -eq "<no package>") { return "<unknown>" }

    $parts = $Package.Split(".")
    return $parts[-1]
}

function Print-CodeBlock {
    param([string]$FilePath)

    Write-Host "      ----- CODE BEGIN -----"
    try {
        Get-Content $FilePath | ForEach-Object {
            Write-Host "      $_"
        }
    } catch {
        Write-Host "      <unable to read file>"
    }
    Write-Host "      ----- CODE END -----"
    Write-Host ""
}

function Scan-Module {
    param([string]$ModuleName)

    Write-Host ""
    Write-Host "============================================================"
    Write-Host " MODULE: $ModuleName"
    Write-Host "============================================================"

    foreach ($src in $sourceSets) {
        $srcPath = Join-Path $BasePath $ModuleName |
                Join-Path -ChildPath "src\$src\kotlin"

        if (-not (Test-Path $srcPath)) { continue }

        Write-Host ""
        Write-Host "  SourceSet: $src"
        Write-Host "  Path: $srcPath"
        Write-Host ""

        # Recursively scan for .kt files
        $files = Get-ChildItem $srcPath -Recurse -Filter *.kt

        foreach ($file in $files) {
            $package = Get-PackageFromFile $file.FullName
            $ktmodule = Get-KtModuleName $package

            if ($ShowFullPackagePath) {
                $packageDisplay = $package
            } else {
                $packageDisplay = ($package -replace "^com\.sildeag\.sound2text\.", "")
            }

            Write-Host "    File: $($file.Name)"
            Write-Host "      Folder: $($file.Directory.FullName)"
            Write-Host "      Package: $packageDisplay"
            Write-Host "      KtModule: $ktmodule"
            Write-Host ""

            if ($ShowCode) {
                Print-CodeBlock -FilePath $file.FullName
            }
        }
    }
}

# Run scanner
foreach ($mod in $Modules) {
    Scan-Module -ModuleName $mod
}
