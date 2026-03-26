plugins {
    id("internal.kmp.compose")
}

// Dynamic platform detection for JavaFX
val platform = System.getProperty("os.name").lowercase().let { os ->
    when {
        os.contains("win") -> "win"
        os.contains("mac") -> if (System.getProperty("os.arch") == "aarch64") "mac-aarch64" else "mac"
        os.contains("linux") -> "linux"
        else -> "win" // Fallback
    }
}

kotlin {
    // Note: jvmToolchain and jvm() are provided by internal.kmp.compose (via internal.kmp.base)

    sourceSets {
        /*
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(project(":ui-common"))
                implementation(project(":stt"))
                //implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
            }
        }
        */
        jvmMain {
            resources.srcDir("src/jvmMain/resources")
            dependencies {
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)

                // Use single-string notation for JavaFX dependencies to avoid deprecation warnings
                libs.bundles.javafx.get().forEach { dep ->
                    val group = dep.module.group
                    val name = dep.module.name
                    val version = dep.versionConstraint.requiredVersion
                    implementation("$group:$name:$version:$platform")
                }
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.testJvm)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.logging)

                libs.bundles.javafx.get().forEach { dep ->
                    val group = dep.module.group
                    val name = dep.module.name
                    val version = dep.versionConstraint.requiredVersion
                    implementation("$group:$name:$version:$platform")
                }
            }
        }
    }
}
/*
compose.desktop {
    application {
        mainClass = "com.sildeag.sound2text.uilegacy.MainKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}
*/