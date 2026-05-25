import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    // Extend base KMP setup (No Android)
    id("internal.kmp.base")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Use the raw extension to avoid accessor generation issues
configure<KotlinMultiplatformExtension> {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.findLibrary("compose-mpp-runtime").get())
                implementation(libs.findLibrary("compose-mpp-foundation").get())
                implementation(libs.findLibrary("compose-mpp-material3").get())
                implementation(libs.findLibrary("compose-mpp-viewmodel").get())
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.findLibrary("compose-mpp-runtime").get())
                implementation(libs.findLibrary("compose-mpp-foundation").get())
                implementation(libs.findLibrary("compose-mpp-material3").get())
                implementation(libs.findLibrary("compose-mpp-viewmodel").get())
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
    }
}
