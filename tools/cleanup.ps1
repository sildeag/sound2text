Write-Host "=== Starting One-Click Cleanup ==="
# -------------------------
# 1. Remove Global Singletons
# -------------------------
WWrite-Host "Scanning for global singletons..."
 Get-ChildItem -Recurse -Filter *.kt |
  ForEach-Object {
  $path = $_.FullName
  $c = Get-Content $path -Raw
  # Ignore DI modules, NavigationState, serializers, and platform objects
  if ($c -match 'object\s+(NavigationState|Screen|Module|
 Serializer|Companion|Platform|Desktop|Android)') {
  return
  }
  # Detect only suspicious global singletons
  if ($c -match 'object\s+\w+Manager') {
  Write-Host "[GLOBAL SINGLETON] Manager singleton in
 $path"
  }
  if ($c -match 'object\s+\w+Service') {
  Write-Host "[GLOBAL SINGLETON] Service singleton in
 $path"
  }
  if ($c -match 'object\s+AppState') {
  Write-Host "[GLOBAL SINGLETON] AppState singleton in
 $path"
  }


# -------------------------
# 2. StorageService Cleanup
# -------------------------
Write-Host "Cleaning StorageService usage..."
Get-ChildItem -Recurse ui-legacy -Filter *.kt |
        ForEach-Object {
            $c = Get-Content $_.FullName
            $c = $c -replace 'StorageServices\.getInstance\(\)',
            'get<StorageService>()'
            $c = $c -replace 'StorageServices\.save',
            'get<StorageService>().save'
            $c = $c -replace 'StorageServices\.load',
            'get<StorageService>().load'
            $c = $c -replace 'StorageServices\.delete',
            'get<StorageService>().delete'
            $c = $c -replace 'SqliteStorageService\(', '// TODO: use StorageService via DI: SqliteStorageService('
            $c = $c -replace 'FileStorageService\(', '// TODO: use StorageService via DI: FileStorageService('
            $c = $c -replace 'AndroidSqliteDriver\(', '// TODO: move to platform module + DI: AndroidSqliteDriver('
            $c = $c -replace 'DesktopSqliteDriver\(', '// TODO: move to platform module + DI: DesktopSqliteDriver('
            Set-Content $_.FullName $c
        }
# -------------------------
# 3. Remove Redux Patterns
# -------------------------
Write-Host "Removing Redux patterns..."
Get-ChildItem -Recurse -Filter *.kt |
        ForEach-Object {
            $c = Get-Content $_.FullName
            $c = $c -replace 'dispatch\(', '// TODO: remove Redux dispatch: dispatch('
            $c = $c -replace 'sealed class .*Event', '// TODO: remove Redux event sealed class'
            Set-Content $_.FullName $c
        }
# -------------------------
# 4. Remove Platform Leaks
# -------------------------
Write-Host "Removing platform leaks..."
Get-ChildItem -Recurse -Filter *.kt |
        ForEach-Object {
            $c = Get-Content $_.FullName
            $c = $c -replace 'Context', '// TODO: inject platform context
via DI: Context'
            $c = $c -replace 'SharedPreferences', '// TODO: move to
platform storage + DI: SharedPreferences'
            Set-Content $_.FullName $c
        }
# -------------------------
# 5. Remove Engine Leaks
# -------------------------
Write-Host "Removing engine leaks..."
Get-ChildItem -Recurse -Filter *.kt |
        ForEach-Object {
            $c = Get-Content $_.FullName
            $c = $c -replace 'SqliteStorageService\(', '// TODO: use StorageService via DI: SqliteStorageService('
            $c = $c -replace 'FileStorageService\(', '// TODO: use StorageService via DI: FileStorageService('
            $c = $c -replace 'import .*engine', '// TODO: remove engine import'
            Set-Content $_.FullName $c
        }
# -------------------------
# 6. ViewModel Extraction
# -------------------------
Write-Host "Extracting ViewModels..."
$uiFiles = Select-String -Path "ui-legacy/**/*.kt" -Pattern "Composable|Fragment|Activity"
foreach ($hit in $uiFiles) {
    $file = $hit.Path
    $name = [System.IO.Path]::GetFileNameWithoutExtension($file)
    $vmName = "${name}ViewModel"
    $vmPath = "ui-legacy/viewmodels/${vmName}.kt"
    if (-not (Test-Path $vmPath)) {
        @"
package ui_legacy.viewmodels
import androidx.lifecycle.ViewModel
import com.sildeag.domain.storage.StorageService
class $vmName(
 private val storage: StorageService
) : ViewModel() {
 // TODO: migrate logic from $name.kt into this ViewModel
}
"@ | Set-Content $vmPath
    }
    Write-Host "Add to DI: viewModel { $vmName(get()) }"
}
Write-Host "=== Cleanup Complete ==="