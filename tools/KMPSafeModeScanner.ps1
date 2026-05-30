# ============================================
# Full KMP SAFE-MODE Modernization Scanner
# ============================================
Write-Host "=== KMP Modernization Scanner (SAFE MODE — READ ONLY)
===" -ForegroundColor Cyan
# --------------------------------------------
# 1. Auto-discover modules from settings.gradle.kts
# --------------------------------------------
$settingsPath = "./settings.gradle.kts"

if (-not (Test-Path $settingsPath)) {
    Write-Host "ERROR: settings.gradle.kts not found. Run this from project root." -ForegroundColor Red
    exit 1
}

$settings = Get-Content $settingsPath -Raw

# Extract include(...) block (multiline-safe)
$includeBlock = [regex]::Match(
        $settings,
        'include\s*\((.*?)\)',
        'Singleline'
).Groups[1].Value

# Extract module names inside the block
$modules = [regex]::Matches($includeBlock, '":([^"]+)"') |
        ForEach-Object { $_.Groups[1].Value }

Write-Host "`nDiscovered modules:" -ForegroundColor Yellow
$modules | ForEach-Object { Write-Host " - $_" }

# --------------------------------------------
# 2. Define KMP source sets
# --------------------------------------------
$sourceSets = @("commonMain", "androidMain", "jvmMain", "main")
# --------------------------------------------
# 3. Define scanning categories
# --------------------------------------------
$Patterns = @{
    "Redux" = @(
        "store\.dispatch\(",
        "sealed class (AppEvent|AppAction|AppState|.*Reducer)",
        "fun\s+\w*Reducer"
    )
    "Platform Leak" = @(
        "\bContext\b",
        "java\.io\.File",
        "android\.",
        "java\.awt"
    )
    "Blocking IO" = @(
        "File\(",
        "\.readText\(",
        "\.writeText\("
    )
    "DI Misuse" = @(
        "=\s*new\s+\w+\(",
        "object\s+\w+Manager",
        "object\s+\w+Service"
    )
    "UI Logic Leak" = @(
        "@Composable.*save\(",
        "@Composable.*load\(",
        "@Composable.*Service\("
    )
    "Architecture Smell" = @(
        "StorageServices\.getInstance",
        "SqliteStorageService\(",
        "FileStorageService\(",
        "AppState\."
    )
    "Suspicious Import" = @(
        "import\s+sound2text\.",
        "import\s+.*legacy",
        "import\s+.*internal"
    )
    "Code Smell" = @(
        "TODO",
        "FIXME"
    )
}
# --------------------------------------------
# 4. Scan each module + source set
# --------------------------------------------
$totalWarnings = 0
foreach ($module in $modules) {
    foreach ($set in $sourceSets) {
        $path = ".\$module\src\$set\kotlin"
        if (-not (Test-Path $path)) { continue }
        Write-Host "`nScanning $path" -ForegroundColor Cyan
        $files = Get-ChildItem -Path $path -Recurse -Filter "*.kt"
        foreach ($file in $files) {
            $content = Get-Content $file.FullName -Raw
            $lines = $content -split "`n"
            $printedHeader = $false
            for ($i = 0; $i -lt $lines.Count; $i++) {
                $line = $lines[$i].Trim()
                $lineNum = $i + 1
                foreach ($category in $Patterns.Keys) {
                    foreach ($pattern in $Patterns[$category]) {
                        if ($line -match $pattern) {
                            if (-not $printedHeader) {
                                Write-Host "--------------------------------------------------" -ForegroundColor Gray
                                Write-Host " FILE: $($file.FullName.Replace((Get-Location).Path, ''))" -ForegroundColor White -BackgroundColor DarkBlue
                                $printedHeader = $true
                            }
                            Write-Host " Line $lineNum [$category]:" -ForegroundColor Yellow
                            Write-Host " '$line'" -ForegroundColor Gray
                            # Suggestions
                            switch ($category) {
                                "Redux" {
                                    Write-Host " Suggestion: Replace Redux patterns with ViewModel + StateFlow." -ForegroundColor Green
                                }
                                "Platform Leak" {
                                    Write-Host " Suggestion: Move platform APIs to androidMain/jvmMain implementations." -ForegroundColor Green
                                }
                                "Blocking IO" {
                                    Write-Host " Suggestion: Wrap file I/O in withContext(Dispatchers.IO)." -ForegroundColor Green
                                }
                                "DI Misuse" {
                                    Write-Host " Suggestion: Replace manual construction with DI container bindings." -ForegroundColor Green
                                }
                                "UI Logic Leak" {
                                    Write-Host " Suggestion: Move logic out of Composables into ViewModels." -ForegroundColor Green
                                }
                                "Architecture Smell" {
                                    Write-Host " Suggestion: Replace legacy architecture patterns with modern KMP modules." -ForegroundColor Green
                                }
                                "Suspicious Import" {
                                    Write-Host " Suggestion: Verify module boundaries and remove incorrect imports." -ForegroundColor Green
                                }
                                "Code Smell" {
                                    Write-Host " Suggestion: Review TODO/FIXME and resolve or document." -ForegroundColor Green
                                }
                            }
                            Write-Host ""
                            $totalWarnings++
                        }
                    }
                }
            }
        }
    }
}
# --------------------------------------------
# 5. Summary
# --------------------------------------------
Write-Host "`n=== Scan Complete ===" -ForegroundColor Cyan
Write-Host "Total warnings: $totalWarnings" -ForegroundColor Yellow
Write-Host "SAFE MODE: No files were modified." -ForegroundColor Green
Write-Host "============================================"