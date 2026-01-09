import org.jetbrains.compose.desktop.application.dsl.TargetFormat.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)   // Required for Kotlin 2.x
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

val platform = "win" // JavaFX native classifier

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {

        val jvmMain by getting {
            resources.srcDir("src/jvmMain/resources")
            dependencies {
                implementation(project(":core"))
                implementation(project(":ui-shared"))
                implementation(compose.desktop.currentOs)
                //{
                //    exclude("org.jetbrains.compose.material", "material")
                //}

                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)

                // JavaFX platform-specific modules
                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.desktop.currentOs)
                //{
                //    exclude("org.jetbrains.compose.material", "material")
                //}
                //implementation(kotlin("test"))
                implementation(libs.bundles.testJvm)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)

                // JavaFX platform-specific modules
                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.sildeag.sound2text.desktop.LauncherKt"

        nativeDistributions {
            targetFormats(Dmg, Msi, Deb)

            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}

dokka {
    moduleName.set(":desktop-ui")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}
