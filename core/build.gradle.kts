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
        namespace = "com.sildeag.sound2text.core"
    }

    sourceSets {
        commonMain {
            // commonMain dependencies are in internal.kmp.base
        }
        
        androidMain {
            dependencies {
                implementation(libs.vosk.android)
            }
        }
    }
}
