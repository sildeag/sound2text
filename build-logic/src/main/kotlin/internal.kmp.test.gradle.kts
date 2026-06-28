import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Shared KMP Testing Convention Plugin
 * Configures commonTest, jvmTest (JUnit 5), and Android unit tests.
 */

plugins {
    id("internal.kmp.compose.library")

    //id("org.jetbrains.kotlin.multiplatform")
    //id("com.android.kotlin.multiplatform.library")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Access the Kotlin Multiplatform Extension
configure<KotlinMultiplatformExtension> {
    sourceSets {
        // commonTest: Shared across all platforms
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.findLibrary("koin-test").get())
            implementation(libs.findLibrary("coroutines-test").get())
        }

        // jvmTest: Specific to Desktop/JVM
        jvmTest.dependencies {
            implementation(libs.findBundle("testJvm").get())
        }
    }

    // Configure all JVM targets to use JUnit Platform (required for JUnit 5)
    targets.configureEach {
        if (this is org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget) {
            testRuns.configureEach {
                executionTask.configure {
                    useJUnitPlatform()
                }
            }
        }
    }
}
