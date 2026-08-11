plugins {
    id("internal.kmp.compose.library")
}

val jfxVersion = libs.versions.javafx.ver.get()

kotlin {
    android {
        namespace = "com.sildeag.sound2text.ui.common"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":platform"))
            //implementation( project(":appcommon"))
            implementation(libs.bundles.itext)
            // implementation(libs.vosk.api)
        }
        
        jvmMain.dependencies {

            implementation("org.openjfx:javafx-base:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-controls:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-fxml:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-graphics:$jfxVersion:$desktopPlatform")
/*
              example of old way to set JavaFX modules
                implementation(compose.desktop.currentOs)
                libs.bundles.javafx.get().forEach { dep ->
                    val group = dep.module.group
                    val name = dep.module.name
                    val version = dep.versionConstraint.requiredVersion
                    implementation("$group:$name:$version:$platform")
                }

*/
        }

        androidMain.dependencies {
        }

        jvmTest.dependencies {
            implementation(libs.bundles.testJvm)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.sildeag.sound2text.uicommon.MainKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}
