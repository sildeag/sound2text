plugins {
    id("internal.kmp.compose.library")
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
    // The modern way to configure Android for KMP in AGP 9.1+
    android {
        namespace = "com.sildeag.sound2text.ui.common"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
            }
        }
        
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
                libs.bundles.javafx.get().forEach { dep ->
                    val group = dep.module.group
                    val name = dep.module.name
                    val version = dep.versionConstraint.requiredVersion
                    implementation("$group:$name:$version:$platform")
                }
            }
        }

        androidMain {
            // Android specific dependencies
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.testJvm)
            }
        }
    }
}
