Write-Host "Checking for missing test dependencies..."

# Patterns that imply required test dependencies
$patterns = @{
    "compose-mpp-viewmodel" = "ViewModel|viewModelScope"
    "koin-compose"          = "getKoin|inject|Koin"
    "compose-mpp-ui-test"   = "createComposeRule|onNode|performClick"
}

# Directories to skip entirely
$skip = @("build-logic", ".gradle", "build")

# Walk all modules
Get-ChildItem -Recurse -Directory |
        Where-Object { $skip -notcontains $_.Name } |
        ForEach-Object {
            $module = $_.FullName
            $testDir = Join-Path $module "src/commonTest"

            if (-not (Test-Path $testDir)) { return }

            $ktFiles = Get-ChildItem $testDir -Recurse -Filter *.kt
            if ($ktFiles.Count -eq 0) { return }

            $buildFile = Join-Path $module "build.gradle.kts"
            if (-not (Test-Path $buildFile)) { return }
            $buildText = Get-Content $buildFile -Raw

            foreach ($dep in $patterns.Keys) {
                $regex = $patterns[$dep]

                $used = $ktFiles | Where-Object {
                    Select-String -Path $_.FullName -Pattern $regex -Quiet
                }

                if ($used -and ($buildText -notmatch $dep)) {
                    Write-Host "[$($_.Name)] Missing test dependency: $dep"
                }
            }
        }
