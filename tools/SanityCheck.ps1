# ================================
# Import Sanity Checker (KMP)
# Sound2Text Edition
# ================================
# --- Module → Package Map ---
$modulePackages = @{
    ":core" = @("com.sound2text.core",
    "com.sound2text.database", "com.sound2text.repository")
    ":appcommon" = @("com.sound2text.appcommon")
    ":ui-common" = @("com.sound2text.ui.common")
    ":ui-android" = @("com.sound2text.ui.android")
    ":ui-desktop" = @("com.sound2text.ui.desktop")
    ":feature-settings"= @("com.sound2text.feature.settings")
    ":di" = @("com.sound2text.di")
}
# --- Scan all Kotlin files ---
$kotlinFiles = Get-ChildItem -Recurse -Filter *.kt
Write-Host "=== Import Sanity Report ===`n"
foreach ($file in $kotlinFiles) {
    $content = Get-Content $file.FullName
    # Detect unresolved references from compiler output or missing imports
    $unresolved = $content |
            Select-String -Pattern "Unresolved reference" |
            ForEach-Object {
                ($_ -split "Unresolved reference:")[1].Trim()
            }
    if ($unresolved.Count -gt 0) {
        Write-Host "`nFile: $($file.FullName)" -ForegroundColor Cyan
        foreach ($symbol in $unresolved) {
            Write-Host " Unresolved: $symbol" -ForegroundColor
            Yellow
            Write-Host " Suggestions:"
            foreach ($pkgList in $modulePackages.Values) {
                foreach ($pkg in $pkgList) {
                    Write-Host " import $pkg.$symbol"
                }
            }
        }
    }
}
Write-Host "`nDone."