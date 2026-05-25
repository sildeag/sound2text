# check-package-vs-folder.ps1
# Validates that Kotlin package names match folder structure
Write-Host " Checking package vs folder structure..." 🔍 -ForegroundColor Cyan
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$modules = @(
    "appcommon","core","di",
    "feature-history","feature-recording","feature-settings",
    "pdf-android","pdf-desktop",
    "stt-android","stt-desktop",
    "ui-android","ui-common","ui-desktop","ui-legacy"
)
foreach ($module in $modules) {
    $modulePath = Join-Path $root "..\$module"
    if (-not (Test-Path $modulePath)) {
        Write-Host " Module not found: $module" ⚠️
        continue
    }
    Write-Host "`n 📦 Checking module: $module" -ForegroundColor Yellow
    $ktFiles = Get-ChildItem -Path $modulePath -Recurse -Filter *.kt
    foreach ($file in $ktFiles) {
        $content = Get-Content $file.FullName -Raw
        # Extract declared package
        if ($content -match 'package\s+([A-Za-z0-9\._]+)') {
            $declared = $matches[1]
        } else {
            Write-Host " Missing package declaration: $ ❌
($file.FullName)" -ForegroundColor Red
            continue
        }
        # Compute expected package from folder path
        $relative = $file.FullName.Substring($modulePath.Length)
        $relative = $relative -replace '\\','/' # normalize slashes
        # Extract path after src/.../kotlin/
        if ($relative -match 'src/.+?/kotlin/(.+)/[^/]+\.kt$') {
            $folderPath = $matches[1] -replace '/','.'
        } else {
            continue
        }
        # Compare
        if ($declared -ne $folderPath) {
            Write-Host " Package mismatch:" -ForegroundColor Red ❌
            Write-Host " File: $($file.FullName)"
            Write-Host " Declared: $declared"
            Write-Host " Expected: $folderPath"
        }
    }
}
Write-Host "`n Package check complete." -ForegroundColor Green