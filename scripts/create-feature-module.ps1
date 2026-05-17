param(
    [Parameter(Mandatory = $true)]
    [string]$FeatureName
)
# Convert names
$lower = $FeatureName.ToLower()
$camel = ($FeatureName -replace '(^|_)([a-z])',
{ $args[0].Groups[2].Value.ToUpper() })
$moduleDir = "feature-$lower"
Write-Host "Creating feature module: $moduleDir"
# Create directories
New-Item -ItemType Directory -Force -Path
"$moduleDir/src/commonMain/sound2text/feature/$lower" | Out-Null
New-Item -ItemType Directory -Force -Path "$moduleDir/src/androidMain" | Out-Null
New-Item -ItemType Directory -Force -Path "$moduleDir/src/jvmMain" | Out-Null
# -----------------------------
# build.gradle.kts
# -----------------------------
@"
plugins {
 id("internal.kmp.library")
}
kotlin {
 sourceSets {
 commonMain {
 dependencies {
 implementation(project(":core"))
 implementation(project(":ui"))
 implementation(libs.koin.core)
 implementation(libs.compose.runtime)
 }
 }
 androidMain { }
 jvmMain { }
 }
}
"@ | Set-Content "$moduleDir/build.gradle.kts"
# -----------------------------
# FeatureEntry
# -----------------------------
@"
package sound2text.feature.$lower
import androidx.compose.runtime.Composable
import sound2text.core.features.FeatureEntry
class ${camel}FeatureEntry(
 private val viewModel: ${camel}ViewModel
) : FeatureEntry {
 override val route: String = "$lower"
 @Composable
 override fun Content() {
 ${camel}Screen(viewModel)
 }
}
"@ | Set-Content "$moduleDir/src/commonMain/sound2text/feature/$lower/${camel}
FeatureEntry.kt"
# -----------------------------
# FeatureDescriptor
# -----------------------------
@"
package sound2text.feature.$lower
import sound2text.core.features.FeatureDescriptor
class ${camel}FeatureDescriptor : FeatureDescriptor {
 override val name: String = "$camel"
 override val version: String = "1.0.0"
}
"@ | Set-Content "$moduleDir/src/commonMain/sound2text/feature/$lower/${camel}
FeatureDescriptor.kt"
# -----------------------------
# FeatureCapabilities
# -----------------------------
@"
package sound2text.feature.$lower
import sound2text.core.capabilities.FeatureCapabilities
class ${camel}FeatureCapabilities : FeatureCapabilities {
 override val requiresMicrophone = false
 override val supportsOfflineMode = true
 override val needsPulseLogic = false
}
"@ | Set-Content "$moduleDir/src/commonMain/sound2text/feature/$lower/${camel}
FeatureCapabilities.kt"
# -----------------------------
# ViewModel
# -----------------------------
@"
package sound2text.feature.$lower
import androidx.lifecycle.ViewModel
class ${camel}ViewModel : ViewModel() {
 // Feature logic here
}
"@ | Set-Content "$moduleDir/src/commonMain/sound2text/feature/$lower/${camel}
ViewModel.kt"
# -----------------------------
# Screen
# -----------------------------
@"
package sound2text.feature.$lower
import androidx.compose.runtime.Composable
@Composable
fun ${camel}Screen(viewModel: ${camel}ViewModel) {
 // UI here
}
"@ | Set-Content "$moduleDir/src/commonMain/sound2text/feature/$lower/${camel}
Screen.kt"
# -----------------------------
# DI Module (printed to console)
# -----------------------------
Write-Host ""
Write-Host "Add this DI module to :di/src/commonMain/sound2text/di/${lower}
Module.kt:"
Write-Host "------------------------------------------------------------"
@"
package sound2text.di
import org.koin.core.qualifier.named
import org.koin.dsl.module
import sound2text.core.features.FeatureEntry
import sound2text.core.features.FeatureDescriptor
import sound2text.core.capabilities.FeatureCapabilities
import sound2text.feature.$lower.*
val ${lower}Module = module {
 factory { ${camel}ViewModel() }
 factory<FeatureEntry>(named("$lower")) {
 ${camel}FeatureEntry(get())
 }
 single<FeatureDescriptor>(named("$lower")) {
 ${camel}FeatureDescriptor()
 }
 single<FeatureCapabilities>(named("$lower")) {
 ${camel}FeatureCapabilities()
 }
}
"@ | Write-Host
Write-Host ""
Write-Host "Feature module '$moduleDir' created successfully."