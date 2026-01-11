plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmp)
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
    // KMP
    jvmToolchain(17)

    jvm()
    
    androidLibrary {
        namespace = "com.sildeag.sound2text.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        
        withHostTest {}
        
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":config"))
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":config"))
                implementation(kotlin("test"))
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(project(":config"))
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.snakeyaml)
                implementation(libs.kotlinx.serialization.json)
                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":config"))
                implementation(libs.bundles.testJvm)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.logging)
                implementation(libs.snakeyaml)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.vosk.android)
            }
        }
        
        val androidHostTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

dokka {
    moduleName.set(":core")
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
