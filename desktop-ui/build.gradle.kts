import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

val platform = "win" // Adjust as needed for native JavaFX packaging

kotlin {
    jvm("desktop") {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.misc.vosk)
                implementation(libs.bundles.logging)
                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString() // e.g., "org.openjfx:javafx-base"
                    val version = it.versionConstraint.requiredVersion // e.g., "20"
                    implementation("$module:$version:$platform")
                }

                /*
                libs.bundles.javafx.get().forEach {
                    implementation("${it.get()}:$platform")
                }

                 */
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.desktop.currentOs)
                implementation(kotlin("test"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}




