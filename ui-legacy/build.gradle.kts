import org.openjfx.gradle.JavaFXOptions

plugins {
    //id("internal.kmp.base")  was
    id("internal.kmp.compose")
    id("org.openjfx.javafxplugin")
}

val jfxVersion = libs.versions.javafx.ver.get()

kotlin {
    // Standard KMP JVM target
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":feature-recording"))
            implementation(project(":ui-common"))
            implementation(project(":pdf-desktop"))
            implementation(project(":stt-desktop"))
            implementation(libs.bundles.itext)
            implementation(libs.vosk)
        }

        jvmMain.dependencies {
            // Using the clean extension property from build-logic
            implementation("org.openjfx:javafx-base:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-controls:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-fxml:$jfxVersion:$desktopPlatform")
            implementation("org.openjfx:javafx-graphics:$jfxVersion:$desktopPlatform")
        }

        jvmTest.dependencies {
                implementation(libs.bundles.testJvm)
        }
    }
}

// Robust configuration for the JavaFX plugin to avoid red underlines
configure<JavaFXOptions> {
    version = jfxVersion
    modules("javafx.controls", "javafx.fxml", "javafx.graphics")
}

/**
 * Entry point task for the JavaFX Legacy UI.
 * Run this task to start the application.
 */
tasks.register<JavaExec>("runLegacy") {
    group = "application"
    description = "Runs the legacy JavaFX application"
    
    mainClass.set("com.sildeag.sound2text.uilegacy.MainKt")
    
    val jvmTarget = kotlin.targets.getByName("jvm")
    val mainCompilation = jvmTarget.compilations.getByName("main")
    
    classpath = mainCompilation.output.allOutputs + 
                configurations.getByName("jvmRuntimeClasspath")
}

/*
// The kmp.compose version kept as a reference:
plugins {
    id("internal.kmp.compose")
}

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
*/
