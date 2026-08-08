plugins {
    id("internal.kmp.compose.library")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    // Modern way to configure Android in a Kotlin-first KMP project in AGP 9.1+
    android {
        namespace = "com.sildeag.sound2text.sttdesktop"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            //implementation(project(":di"))
        }

        
        jvmMain.dependencies {
            implementation(libs.vosk.model.en)
            implementation(libs.vosk.core)
        }

        androidMain.dependencies {
            implementation(libs.vosk.android)
        }

        jvmTest.dependencies {
            implementation(libs.bundles.testJvm)
        }
    }
}
/*
plugins {
    id("internal.kmp.jvm.library") // The new plugin
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(libs.vosk)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.vosk.api)
                // Add your JavaFX dependencies here for the STT implementation
            }
        }
    }
}
*/