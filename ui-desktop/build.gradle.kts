plugins {
    id("internal.kmp.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(project(":ui-common"))
                implementation(project(":stt-desktop"))
                implementation(compose.desktop.currentOs)
            }
        }
        
        jvmMain {
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
