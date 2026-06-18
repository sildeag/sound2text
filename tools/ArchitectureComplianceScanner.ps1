# ============================================
# SOUND2TEXT ARCHITECTURE COMPLIANCE SCANNER
# Read-only, safe to run anytime
# ============================================
param(
    [string]$Root = ".",
    [string]$Output = "architecture-scan.log"
)
$oldPatterns = @(
    "PdfManager",
    "SttManager",
    "AudioManager",
    "RepositoryManager",
    "ServiceLocator",
    "GlobalScope",
    "MutableLiveData",
    "android\.speech",
    "iText7",
    "PdfReader",
    "PdfWriter",
    "FileInputStream",
    "java\.io\.File",
    "SpeechRecognizer",
    "Callback",
    "Singleton",
    "object\s+\w+\s*{"
)
$newPatterns = @(
    "Engine",
    "Controller",
    "Provider",
    "Plugin",
    "Repository",
    "State",
    "ViewModel",
    "Wizard",
    "Config",
    "Flow",
    "suspend",
    "interface",
    "expect",
    "actual"
)
$platformLeaks = @(
    "android\.",
    "java\.io\.File",
    "javax\.",
    "javafx\.",
    "sun\."
)
$uiLeaksIntoCore = @(
    "androidx\.compose",
    "androidx\.lifecycle",
    "ViewModel",
    "Composable",
    "remember",
    "mutableStateOf"
)
$diViolations = @(
    "startKoin",
    "module\s*{",
    "get\(",
    "single\s*<",
    "factory\s*<"
)
$results = @()
Write-Host "Scanning project at $Root..."
Get-ChildItem -Path $Root -Recurse -Include *.kt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $oldHits = $oldPatterns | Where-Object { $content -match $_ }
    $newHits = $newPatterns | Where-Object { $content -match $_ }
    $platformHits = $platformLeaks | Where-Object { $content -match
            $_ }
    $uiHits = $uiLeaksIntoCore | Where-Object { $content -match $_ }
    $diHits = $diViolations | Where-Object { $content -match $_ }
    $results += [PSCustomObject]@{
        File = $file
        OldCode = ($oldHits -join ", ")
        NewCode = ($newHits -join ", ")
        PlatformLeak = ($platformHits -join ", ")
        UiLeak = ($uiHits -join ", ")
        DiViolation = ($diHits -join ", ")
    }
}
$results | Out-File $Output
Write-Host "Scan complete. Results saved to $Output"