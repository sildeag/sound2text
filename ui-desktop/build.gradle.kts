plugins {
    id("internal.kmp.compose")
    //id("internal.kmp.compose.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            //implementation(project(":core"))
            implementation(project(":ui-common"))
            implementation(project(":feature-recording"))
            //implementation(project(":pdf-desktop"))
            //implementation(project(":stt-desktop"))

            //implementation(project(":stt-desktop"))
            implementation(compose.desktop.currentOs)
            //implementation(libs.compose.material3)
            implementation(libs.bundles.itext)
            implementation(libs.vosk)
            implementation(libs.vosk.api)
        }

        jvmMain {
            dependencies {
            }
            resources.srcDir("src/jvmMain/resources")
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.sildeag.sound2text.desktop.LauncherKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}
