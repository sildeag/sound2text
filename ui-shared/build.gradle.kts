import org.jetbrains.compose.desktop.application.dsl.TargetFormat.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)   // Required for Kotlin 2.x
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

val platform = "win" // JavaFX native classifier

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
            }
        }


        val jvmMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.desktop.currentOs)
                //{
                //    exclude("org.jetbrains.compose.material", "material")
                //}
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

        val jvmTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.desktop.currentOs)
                //{
                //    exclude("org.jetbrains.compose.material", "material")
                //}
                implementation(kotlin("test"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(Dmg, Msi, Deb)

            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}

dokka {
    moduleName.set(":ui-shared")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}
