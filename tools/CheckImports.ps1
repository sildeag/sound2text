$root = "D:\Android\Projects\sound2text"

$patterns = @{
    "androidx"     = "androidx"
    "iText"        = "com\.itextpdf"
    "JavaImports"  = "^import\s+java\."
    "KotlinImports"= "^import\s+kotlin\."
}

$sourceRules = @{
    "commonMain" = @("androidx", "java.awt", "javax.swing", "PdfRenderer")
    "desktopMain" = @("androidx", "PdfRenderer")
    "androidMain" = @("java.awt", "javax.swing")
}

$result = @()

Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file

    # Determine source set
    $sourceSet = if ($file -like "*commonMain*") { "commonMain" }
    elseif ($file -like "*desktopMain*") { "desktopMain" }
    elseif ($file -like "*androidMain*") { "androidMain" }
    else { "other" }

    # Scan for patterns
    foreach ($key in $patterns.Keys) {
        $regex = $patterns[$key]
        foreach ($line in $content) {
            if ($line -match $regex) {
                $result += "🔍 Found $key import in $file → $line"
            }
        }
    }

    # Check for forbidden imports in this source set
    if ($sourceSet -ne "other") {
        foreach ($bad in $sourceRules[$sourceSet]) {
            if ($content -match $bad) {
                $result += "⚠️ Forbidden import in ${sourceSet}: $file → '$bad'"
            }
        }
    }
}

if ($result.Count -eq 0) {
    $result = "✔ No suspicious imports detected."
}

# Copy to clipboard
$result -join "`n" | Set-Clipboard
Write-Host "Results copied to clipboard." -ForegroundColor Green
