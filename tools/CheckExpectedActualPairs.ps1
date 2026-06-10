$root = "D:\Android\Projects\sound2text"
$expects = @{}
$actuals = @{}
# Collect expect declarations
Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file
    foreach ($line in $content) {
        if ($line -match "expect\s+fun\s+(\w+)") {
            $expects[$matches[1]] = $file
        }
    }
}
# Collect actual declarations
Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file
    foreach ($line in $content) {
        if ($line -match "actual\s+fun\s+(\w+)") {
            $actuals[$matches[1]] = $file
        }
    }
}
$missingActuals = $expects.Keys | Where-Object { -not
$actuals.ContainsKey($_) }
$missingExpects = $actuals.Keys | Where-Object { -not
$expects.ContainsKey($_) }
$result = @()
if ($missingActuals.Count -gt 0) {
    $result += "❌ Missing actual implementations:"
    $missingActuals | ForEach-Object { $result += " - $_ (declared
in $($expects[$_]))" }
}
if ($missingExpects.Count -gt 0) {
    $result += "❌ Missing expect declarations:"
    $missingExpects | ForEach-Object { $result += " - $_
(implemented in $($actuals[$_]))" }
}
if ($result.Count -eq 0) {
    $result = "✔ All expect/actual pairs are complete."
}
# Copy to clipboard
$result -join "`n" | Set-Clipboard
Write-Host "Results copied to clipboard." -ForegroundColor Green