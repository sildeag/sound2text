$root = "D:\Android\Projects\sound2text"
$deps = @{}
Get-ChildItem -Path $root -Recurse -Filter "build.gradle.kts" |
        ForEach-Object {
            $file = $_.FullName
            $module = Split-Path (Split-Path $file -Parent) -Leaf
            $content = Get-Content $file
            $deps[$module] = @()
            foreach ($line in $content) {
                if ($line -match 'project\(":(.+?)"\)') {
                    $deps[$module] += $matches[1]
                }
            }
        }
$cycles = @()
function Visit($start, $current, $visited) {
    if ($visited.Contains($current)) {
        if ($current -eq $start) {
            $cycles += "🔁 Circular dependency detected: $($visited -join ' → ') → $start"
        }
        return
    }
    $visited += $current
    foreach ($next in $deps[$current]) {
        Visit $start $next $visited
    }
}
foreach ($module in $deps.Keys) {
    Visit $module $module @()
}
$result = if ($cycles.Count -gt 0) { $cycles } else { "✔ No circular dependencies found." }
$result -join "`n" | Set-Clipboard
Write-Host "Results copied to clipboard." -ForegroundColor Green