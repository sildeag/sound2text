plugins {
    id("internal.kmp.compose")
}

// Dynamic platform detection for JavaFX
/**
 * Returns the platform string (win, linux, mac, mac-aarch64)
 * for native dependency resolution.
 * Define it once at the top of the file
 * val platform = run {
 *     val os = System.getProperty("os.name").lowercase()
 *     val arch = System.getProperty("os.arch")
 *
 *     when {
 *         os.contains("win")   -> "win"
 *         os.contains("linux") -> "linux"
 *         os.contains("mac")   -> if (arch == "aarch64") "mac-aarch64" else "mac"
 *         else -> "win"
 *     }
 * }
 *
 * dependencies {
 *     implementation("org.openjfx:javafx-base:$jfxVersion:$platform")
 * }
 */



val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML


kotlin {
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
                implementation("org.openjfx:javafx-base:$jfxVersion:$desktopPlatform")
                implementation("org.openjfx:javafx-controls:$jfxVersion:$desktopPlatform")
                implementation("org.openjfx:javafx-fxml:$jfxVersion:$desktopPlatform")
                implementation("org.openjfx:javafx-graphics:$jfxVersion:$desktopPlatform")
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.testJvm)
            }
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
/*
application {
    mainClass.set("com.sildeag.sound2text.uilegacy.MainKt") // Your original JavaFX main class
}

 */