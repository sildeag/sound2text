plugins {
    id("internal.kmp.library")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

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
