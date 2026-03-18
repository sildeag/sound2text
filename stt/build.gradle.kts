plugins {
    id("internal.kmp.compose.library") // Stable Classic Android + Compose
}

// Dynamic platform detection for JavaFX
val platform = System.getProperty("os.name").lowercase().let { os ->
    when {
        os.contains("win") -> "win"
        os.contains("mac") -> if (System.getProperty("os.arch") == "aarch64") "mac-aarch64" else "mac"
        os.contains("linux") -> "linux"
        else -> "win" // Fallback
    }
}

kotlin {
    androidTarget {
        // Classic target configuration
        @Suppress("DEPRECATION")
        publishLibraryVariants("release")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":config"))
                implementation(project(":core"))
                implementation(libs.vosk)
            }
        }
        
        jvmMain {
            dependencies {
                implementation(libs.vosk.api)
                implementation(libs.bundles.itext)
                libs.bundles.javafx.get().forEach { dep ->
                    val group = dep.module.group
                    val name = dep.module.name
                    val version = dep.versionConstraint.requiredVersion
                    implementation("$group:$name:$version:$platform")
                }
            }
        }

        androidMain {
            dependencies {
                implementation(libs.vosk.android)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.testJvm)
            }
        }
    }
}

android {
    namespace = "com.sildeag.sound2text.stt"
}
