$root = "D:/Android/Projects/sound2text"

# 1. BAD/INCORRECT IMPORTS (Plain text substrings)
$incorrectImports = @{
    "import androidx.compose.material." = "❌ Wrong import! You are using Material 2 instead of Material3."
    "import android.view."              = "❌ Android UI leaked into a Multiplatform file."
}

# 2. VALID COMPOSE PATTERN MATCHES & CORRECT MATERIAL 3 IMPORTS
$commonComposeImports = @{
    "@Composable"         = "import androidx.compose.runtime.Composable"
    "Column"              = "import androidx.compose.foundation.layout.Column"
    "Row"                 = "import androidx.compose.foundation.layout.Row"
    "Box"                 = "import androidx.compose.foundation.layout.Box"
    "Text"                = "import androidx.compose.material3.Text"
    "DropdownMenu"        = "import androidx.compose.material3.DropdownMenu"
    "Button"              = "import androidx.compose.material3.Button"
    "remember"            = "import androidx.compose.runtime.remember"
    "mutableStateOf"      = "import androidx.compose.runtime.mutableStateOf"
    "Modifier"            = "import androidx.compose.ui.Modifier"
    "collectAsState"      = "import androidx.compose.runtime.collectAsState"
    "OutlinedTextField"   = "import androidx.compose.material3.OutlinedTextField"
    "Spacer"              = "import androidx.compose.foundation.layout.Spacer"
}

$result = @()

# Use Windows native .NET engine to sweep for files safely
$allFiles = [System.IO.Directory]::GetFiles($root, "*.kt", [System.IO.SearchOption]::AllDirectories)

if ($null -eq $allFiles -or $allFiles.Length -eq 0) {
    Write-Host "⚠️ Warning: Windows .NET found 0 files. Check path: $root" -ForegroundColor Yellow
    Exit
}

$validFileCount = 0

foreach ($file in $allFiles) {
    # Normalized search for build directories using forward slash translation
    $normalizedPath = $file.Replace("\", "/")
    if ($normalizedPath -match "/build/" -or $normalizedPath -match "/\.gradle/") { continue }
    $validFileCount++

    $filename = [System.IO.Path]::GetFileName($file)

    # --- AUTOMATIC MODULE & SOURCESET EXTRACTION ---
    # Figures out the module name based on your root folder structure
    $moduleName = "root"
    if ($normalizedPath -match "sound2text/([^/]+)/src/") {
        $moduleName = $Matches[1]
    }

    # Detects standard KMP source sets dynamically
    $sourceSet = "unknown"
    if ($normalizedPath -match "/commonMain/") { $sourceSet = "commonMain" }
    elseif ($normalizedPath -match "/desktopMain/") { $sourceSet = "desktopMain" }
    elseif ($normalizedPath -match "/jvmMain/") { $sourceSet = "jvmMain" }
    elseif ($normalizedPath -match "/androidMain/") { $sourceSet = "androidMain" }
    elseif ($normalizedPath -match "/uidesktop/") { $sourceSet = "uidesktop" } # Catches your specific structure

    # Read the text string reliably
    $content = [System.IO.File]::ReadAllText($file)
    if ([string]::IsNullOrWhiteSpace($content)) { continue }

    # Standardize spaces and line endings
    $cleanContent = $content.Replace("`r", "").Replace("`n", "")
    $compactContent = $cleanContent.Replace(" ", "").Replace("`t", "")

    $hasIssuesForFile = $false
    # Enhanced header detailing the exact Module and KMP Source Set context
    $fileHeader = "`n📦 Module: [:$moduleName] -> SourceSet: [$sourceSet] | File: $filename`n📍 Path: $file"

    # PART A: Check for bad frameworks
    foreach ($badText in $incorrectImports.Keys) {
        if ($cleanContent.Contains($badText)) {
            if (-not $hasIssuesForFile) { $result += $fileHeader; $hasIssuesForFile = $true }
            $result += "    " + $incorrectImports[$badText]
        }
    }

    # PART B: Check for missing keywords
    foreach ($keyword in $commonComposeImports.Keys) {
        if ($cleanContent.Contains($keyword)) {
            $targetImport = $commonComposeImports[$keyword]
            $compactTarget = $targetImport.Replace(" ", "")

            if (-not $compactContent.Contains($compactTarget)) {
                if (-not $hasIssuesForFile) { $result += $fileHeader; $hasIssuesForFile = $true }
                $result += "   💡 Missing: '$keyword' -> Add: $targetImport"
            }
        }
    }
}

Write-Host "Successfully analyzed $validFileCount active project Kotlin files." -ForegroundColor Cyan

if ($result.Count -eq 0) {
    $result = "✔ All clear! No missing or incorrect Compose imports detected."
}

# Push everything to clipboard array
$result -join "`n" | Set-Clipboard
Write-Host "Scan completed! Results copied to your clipboard." -ForegroundColor Green
