# detect-misplaced-files.ps1
# Scans all modules for misplaced files in commonMain, androidMain,
jvmMain
Write-Host " 🔍 Scanning for misplaced files..." -ForegroundColor Cyan
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
# Define rules
$rules = @(
    @{
        Name = "JVM imports in commonMain"
        Path = "commonMain"
        Pattern = "import\s+java\.|import\s+javax\."
    },
    @{
        Name = "Android imports in commonMain"
        Path = "commonMain"
        Pattern = "import\s+android\."
    },
    @{
        Name = "Desktop/JVM imports in androidMain"
        Path = "androidMain"
        Pattern = "import\s+java\.|import\s+javax\."
    },
    @{
        Name = "Android imports in jvmMain"
        Path = "jvmMain"
        Pattern = "import\s+android\."
    },
    @{
        Name = "Compose UI in non-UI modules"
        Path = "commonMain"
        Pattern = "import\s+androidx\.compose"
    }
)
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

    Write-Host "`n📦 Checking module: $module" -ForegroundColor Yellow

    foreach ($rule in $rules) {
        $searchPath = Join-Path $modulePath "src" | Join-Path -ChildPath $rule.Path
        if (-not (Test-Path $searchPath)) { continue }
        $files = Get-ChildItem -Path $searchPath -Recurse -Filter *.kt
        foreach ($file in $files) {
            $content = Get-Content $file.FullName -Raw
            if ($content -match $rule.Pattern) {
                Write-Host " [$($rule.Name)] $($file.FullName)" ❌ -ForegroundColor Red
            }
        }
    }
}
Write-Host "`n Scan complete." -ForegroundColor Green