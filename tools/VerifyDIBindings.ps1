$root = "D:\Android\Projects\sound2text"
$classes = @{}
$diBindings = @()
# Collect class constructors
Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file
    foreach ($line in $content) {
        if ($line -match "class\s+(\w+)\s*\((.*?)\)") {
            $classes[$matches[1]] = $matches[2]
        }
    }
}
# Collect DI factory bindings
Get-ChildItem -Path $root -Recurse -Filter *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file
    foreach ($line in $content) {
        if ($line -match "factory\s*<(\w+)>") {
            $diBindings += $matches[1]
        }
    }
}
$result = @()
foreach ($binding in $diBindings) {
    if (-not $classes.ContainsKey($binding)) {
        $result += "❌ DI binding for '$binding' but class not found."
        continue
    }
    $ctor = $classes[$binding]
    if ($ctor.Trim() -eq "") {
        continue
    }
    $params = $ctor.Split(",") | ForEach-Object { $_.Trim() }
    foreach ($p in $params) {
        if (-not ($p -match "(\w+):\s*(\w+)")) { continue }
        $type = $matches[2]
        if (-not $classes.ContainsKey($type)) {
            $result += "⚠️ '$binding' constructor requires '$type' but no class found."
        }
    }
}
if ($result.Count -eq 0) {
    $result = "✔ All DI bindings appear valid."
}
$result -join "`n" | Set-Clipboard
Write-Host "Results copied to clipboard." -ForegroundColor Green