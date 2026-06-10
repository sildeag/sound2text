$root = "D:\Android\Projects\sound2text"
$rules = @{
    "commonMain" = @("android", "androidx", "java.awt",
    "javax.swing", "PdfRenderer", "BufferedImage")
    "desktopMain" = @("android", "androidx", "PdfRenderer")
    "androidMain" = @("java.awt", "javax.swing", "BufferedImage")
}
$result = @()
Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file
    $sourceSet = if ($file -like "*commonMain*") { "commonMain" }
    elseif ($file -like "*desktopMain*") { "desktopMain"
    }
    elseif ($file -like "*androidMain*") { "androidMain"
    }
    else { return }
    foreach ($rule in $rules[$sourceSet]) {
        if ($content -match $rule) {
            $result += "⚠️ Suspicious import in $file → '$rule'"
        }
    }
}
if ($result.Count -eq 0) {
    $result = "✔ No platform-leaking imports detected."
}
$result -join "`n" | Set-Clipboard
Write-Host "Results copied to clipboard." -ForegroundColor Green