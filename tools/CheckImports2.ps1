$root = "D:\Android\Projectsound2text"

# 1. BAD/INCORRECT IMPORTS (Common mistakes to flag)
$incorrectImports = @{
    "import androidx.compose.material\."   = "❌ Wrong import! You are using Material 2 instead of Material3."
    "import android\.view\."                = "❌ Android UI leaked into a Multiplatform file."
}

# 2. VALID COMPOSE PATTERN MATCHES & CORRECT MATERIAL 3 IMPORTS
$commonComposeImports = @{
    "@Composable"         = "import androidx.compose.runtime.Composable"
    "\bColumn\b"          = "import androidx.compose.foundation.layout.Column"
    "\bRow\b"             = "import androidx.compose.foundation.layout.Row"
    "\bBox\b"             = "import androidx.compose.foundation.layout.Box"
    "\bText\b"            = "import androidx.compose.material3.Text"
    "\bDropdownMenu\b"    = "import androidx.compose.material3.DropdownMenu"
    "\bButton\b"          = "import androidx.compose.material3.Button"
    "\bremember\b"        = "import androidx.compose.runtime.remember"
    "\bmutableStateOf\b"  = "import androidx.compose.runtime.mutableStateOf"
    "\bModifier\b"        = "import androidx.compose.ui.Modifier"
    "\bcollectAsState\b"  = "import androidx.compose.runtime.collectAsState"
    "\bTextField\b"       = "import androidx.compose.material3.TextField"
    "\bOutlinedTextField\b" = "import androidx.compose.material3.OutlinedTextField"
    "\bSpacer\b"          = "import androidx.compose.foundation.layout.Spacer"
}

$result = @()

# Use -Include with wildcards to bypass strict OS extension caching quirks
$ktFiles = Get-ChildItem -Path $root -Recurse | Where-Object { $_.Name -match '\.kt$' }

# SAFETY WARNING: Print out if the directory can't find anything
if ($ktFiles.Count -eq 0 -or $ktFiles -eq $null) {
    Write-Host "⚠️ Warning: The script scanned your root folder but found exactly 0 Kotlin files. Check your path or save your files!" -ForegroundColor Yellow
} else {
    Write-Host "Found $($ktFiles.Count) Kotlin file(s). Analyzing file content..." -ForegroundColor Cyan
}

foreach ($fileObj in $ktFiles) {
    $file = $fileObj.FullName
    $filename = $fileObj.Name
    $content = Get-Content $file -Raw

    # Skip empty or unsaved blank files
    if ([string]::IsNullOrWhiteSpace($content)) { continue }

    $hasIssuesForFile = $false
    $fileHeader = "`n--- File: $filename ($file) ---"

    # PART A: Check for incorrect/wrong imports
    foreach ($badRegex in $incorrectImports.Keys) {
        if ($content -match $badRegex) {
            if (-not $hasIssuesForFile) {
                $result += $fileHeader
                $hasIssuesForFile = $true
            }
            $result += "    " + $incorrectImports[$badRegex]
        }
    }

    # PART B: Check for missing necessary imports
    foreach ($pattern in $commonComposeImports.Keys) {
        if ($content -match $pattern) {
            $targetImport = $commonComposeImports[$pattern]

            # Escape the target import string to check for it exactly
            $escapedImport = [regex]::Escape($targetImport)
            if ($content -notmatch $escapedImport) {
                if (-not $hasIssuesForFile) {
                    $result += $fileHeader
                    $hasIssuesForFile = $true
                }
                $result += "💡 Hint: Found code pattern '$pattern'. You might need to add:"
                $result += "    $targetImport"
            }
        }
    }
}

if ($result.Count -eq 0) {
    $result = "✔ All clear! No missing or incorrect Compose imports detected."
}

# Copy directly to clipboard
$result -join "`n" | Set-Clipboard
Write-Host "Scan completed! Results copied to your clipboard." -ForegroundColor Green

# "\bYourKeyword\b" = "your.package.path.YourImport"