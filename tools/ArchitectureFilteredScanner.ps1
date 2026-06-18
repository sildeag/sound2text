# ============================================
#  SOUND2TEXT FILTERED ARCHITECTURE SCANNER
#  Only logs files that need attention
#  Read-only, safe to run anytime
# ============================================

param(
    [string]$Root = ".",
    [string]$Output = "architecture-scan-filtered.log"
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

Write-Host "Running filtered architecture scan..."

Get-ChildItem -Path $Root -Recurse -Include *.kt | ForEach-Object {

    $file = $_.FullName
    $content = Get-Content $file -Raw

    $oldHits = $oldPatterns | Where-Object { $content -match $_ }
    $newHits = $newPatterns | Where-Object { $content -match $_ }
    $platformHits = $platformLeaks | Where-Object { $content -match $_ }
    $uiHits = $uiLeaksIntoCore | Where-Object { $content -match $_ }
    $diHits = $diViolations | Where-Object { $content -match $_ }

    # Skip files with no issues
    if (
    $oldHits.Count -eq 0 -and
            $platformHits.Count -eq 0 -and
            $uiHits.Count -eq 0 -and
            $diHits.Count -eq 0
    ) {
        return
    }

    # Log only files that need attention
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

Write-Host "Filtered scan complete. Results saved to $Output"
