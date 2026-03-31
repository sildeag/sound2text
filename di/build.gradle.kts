plugins {
    id("internal.kmp.library")
}

// Dynamic platform detection
val platform = System.getProperty("os.name").lowercase().let { os ->
    when {
        os.contains("win") -> "win"
        os.contains("mac") -> if (System.getProperty("os.arch") == "aarch64") "mac-aarch64" else "mac"
        os.contains("linux") -> "linux"
        else -> "win" // Fallback
    }
}

kotlin {
    android {
        namespace = "com.sildeag.sound2text.di"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
            }
        }
        
        jvmMain {
            dependencies {
                implementation(libs.vosk.api)
                implementation(libs.snakeyaml)
                implementation(libs.bundles.itext)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.vosk.android)
            }
        }
    }
}
