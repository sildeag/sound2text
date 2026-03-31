plugins {
    id("internal.kmp.library")
    //id("internal.jvm")
}

kotlin {
    android {
        namespace = "com.sildeag.sound2text.pdfdesktop"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(libs.bundles.itext)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.snakeyaml)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.vosk.android)
            }
        }
    }
}
