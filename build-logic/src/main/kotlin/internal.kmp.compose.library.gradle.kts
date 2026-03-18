import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.library") // Using the "Classic" stable plugin
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Classic Android configuration
android {
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
}

configure<KotlinMultiplatformExtension> {
    // In the Classic setup, we use androidTarget()
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.findLibrary("compose-mpp-runtime").get())
                implementation(libs.findLibrary("compose-mpp-foundation").get())
                implementation(libs.findLibrary("compose-mpp-material3").get())
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
        
        // Match the source set name for classic Android target
        val androidMain by getting {
            // Android specific common dependencies if any
        }
    }
}
