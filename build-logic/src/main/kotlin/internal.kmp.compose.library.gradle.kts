import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Using configure<T> ensures we are targeting the correct KotlinMultiplatformExtension
// and avoids ambiguity with other 'kotlin' functions in the Gradle DSL.
configure<KotlinMultiplatformExtension> {
    @Suppress("OPT_IN_USAGE")
    android {
        namespace = project.group.toString()
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        // Host tests are enabled and configured via withHostTest
        withHostTest {
            // Configuration for host-side unit tests (standard JUnit/MockK)
        }

        // On-device tests are enabled and configured via withDeviceTest
        withDeviceTest {
            // Configuration for on-device tests
        }

        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(libs.findVersion("jvm").get().requiredVersion))
        }
    }

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                implementation(libs.findLibrary("compose-mpp-runtime").get())
                implementation(libs.findLibrary("compose-mpp-foundation").get())
                implementation(libs.findLibrary("compose-mpp-material3").get())
                implementation(libs.findLibrary("compose-mpp-material-icons-ext").get())
                implementation(libs.findLibrary("compose-mpp-viewmodel").get())
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
    }
}
