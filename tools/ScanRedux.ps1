# ScanRedux.ps1
# Run this from the project root folder
# 1. Define target directories explicitly to capture KMP source sets
$TargetDirs = @("commonMain", "androidMain", "main", "jvmMain", "build-logic")
# 2. Define the patterns to scan for (Regex)
$Patterns = @{
    "Redux Store / Dispatch" = "(?i)\bdispatch\(|\bstore\.dispatch"
    "Redux Action" = "(?i)interface\s+\w*Action\b|sealed\s+class\s+\w*Action\b"
    "Redux Reducer" = "(?i)fun\s+\w*reducer\b|when\(action\)"
    "Redux Middleware" = "(?i)interface\s+\w*Middleware\b|\bmiddleware\b"
}
# 3. Find all relevant .kt files, ignoring common archive/build outputs
Write-Host " Initializing project-wide KMP Redux scan..." 🔍 -ForegroundColor Cyan
$Files = Get-ChildItem -Path . -Recurse -Filter "*.kt" | Where-Object {
    $filePath = $_.FullName
    # Filter for target directories
    $isInTargetDir = $false
    foreach ($dir in $TargetDirs) {
        if ($filePath -match "\\$dir\\") { $isInTargetDir = $true; break }
    }
    # Ignore build, gradle cache, and temporary/archive folders
    $isIgnored = $filePath -match '\\build\\|\\\.gradle\\|\\\.idea\\'
    #$isIgnored = $filePath -match "\\build\\" -or $filePath -match "\\\.gradle\\" -or $filePath -match "\\\.idea\\"
    $isInTargetDir -and -not $isIgnored
}
Write-Host " Found $($Files.Count) Kotlin source files to analyze.`n" 📂 -ForegroundColor Yellow
# 4. Scan files line by line
foreach ($File in $Files) {
    $Lines = Get-Content -Path $File.FullName
    $FilePrinted = $false
    for ($i = 0; $i -lt $Lines.Count; $i++) {
        #$LineText = ([string]$Lines[$i]).Trim() # This fails for null not having Trim
        if ($null -ne $Lines[$i]) {
            $LineText = ([string]$Lines[$i]).Trim()
            #$LineText = $Lines[$i].Trim() # This fails because blank lines are not strings
        }
        #$LineText = $Lines[$i].Trim() # This fails due to null or empty lines not having Trim
        $LineNum = $i + 1
        foreach ($PatternName in $Patterns.Keys) {
            if ($LineText -match $Patterns[$PatternName]) {
                # Print File Header only once per file to keep output scannable
                if (-not $FilePrinted) {
                    Write-Host "--------------------------------------------------" -ForegroundColor Gray
                    Write-Host " FILE: $($File.FullName.Replace((Get-Location).Path, ''))" -ForegroundColor White -BackgroundColor DarkBlue
                    $FilePrinted = $true
                }
                Write-Host " Line $LineNum [$PatternName]: " 📍 -NoNewline -ForegroundColor Yellow
                Write-Host "'$LineText'" -ForegroundColor Gray
                # Generate code-comment advice based on what was found
                Write-Host " MVVM Migration Suggestion:" 💡 -ForegroundColor Green
                switch ($PatternName) {
                    "Redux Store / Dispatch" {
                        Write-Host " // TODO: Replace dispatch call with a direct ViewModel
method invocation." -ForegroundColor Green
                        Write-Host " // Example: Instead of store.dispatch(Action), use:
viewModel.executeAction()" -ForegroundColor Green
                    }
                    "Redux Action" {
                        Write-Host " // TODO: Convert Redux Actions into sealed classes or
individual ViewModel UI Event functions." -ForegroundColor Green
                        Write-Host " // Example: fun onUserClick() { viewModelScope.launch
{ ... } }" -ForegroundColor Green
                    }
                    "Redux Reducer" {
                        Write-Host " // TODO: Migrate state mutation logic to a MutableStateFlow
inside your ViewModel." -ForegroundColor Green
                        Write-Host " // Example: _uiState.update { current ->
current.copy(property = newValue) }" -ForegroundColor Green
                    }
                    "Redux Middleware" {
                        Write-Host " // TODO: Extract asynchronous side-effects out of global
middleware chains." -ForegroundColor Green
                        Write-Host " // Example: Inject a KMP Domain Repository or UseCase
directly into the ViewModel constructor." -ForegroundColor Green
                    }
                }
                Write-Host "" # Blank spacer line
            }
        }
    }
}
Write-Host " Scan complete." ✅ -ForegroundColor Cyan