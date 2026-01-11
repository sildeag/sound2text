// import org.jetbrains.compose.desktop.application.dsl.TargetFormat.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmp)
    //alias(libs.plugins.composeMultiplatform)
    //alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    jacoco
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
    jvmToolchain(17)
    jvm()

    androidLibrary {
        namespace = "com.sildeag.sound2text.stt"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        
        withHostTest {}
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":config"))
                implementation(project(":core"))
                implementation(libs.koin.core)
                implementation(libs.vosk.api)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        jvmMain {
            resources.srcDir("src/jvmMain/resources")
            dependencies {
                //implementation(compose.desktop.currentOs)
                implementation(project(":config"))
                implementation(project(":core"))
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.vosk)

                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.testJvm)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                //implementation(compose.desktop.currentOs)

                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }
        
        val androidMain by getting {
            dependencies {
                // Android specific if any
            }
        }

        // withHostTest {} creates androidHostTest and androidHostUnitTest
        val androidHostTest by getting {
             dependencies {
                // Android specific if any
            }
        }
    }
}

/* 
// Commented out until composeMultiplatform plugin is enabled
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
*/

dokka {
    moduleName.set(":stt")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("jvmTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val mainSrc = files("src/commonMain/kotlin", "src/jvmMain/kotlin")
    sourceDirectories.setFrom(mainSrc)

    classDirectories.setFrom(
        fileTree(layout.buildDirectory.dir("classes/kotlin/jvm/main")) {
            exclude(
                "**/commonTest/**",
                "**/jvmTest/**"
            )
        }
    )

    executionData.setFrom(
        files(layout.buildDirectory.file("jacoco/jvmTest.exec"))
    )
}
