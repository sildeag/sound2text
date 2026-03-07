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
                implementation(libs.bundles.itext)
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
                implementation(libs.bundles.itext)
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
    // Use the version from your catalog for better compatibility
    toolVersion = libs.versions.jacoco.ver.get()
}

// Jacoco report configuration for JVM target
tasks.register<JacocoReport>("jacocoTestReport") {
    val testTask = tasks.named<Test>("jvmTest")
    dependsOn(testTask)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    sourceDirectories.setFrom(files("src/commonMain/kotlin", "src/jvmMain/kotlin"))

    classDirectories.setFrom(
        fileTree(layout.buildDirectory.dir("classes/kotlin/jvm/main")) {
            exclude(
                "**/commonTest/**",
                "**/jvmTest/**"
            )
        }
    )

    executionData.setFrom(
        layout.buildDirectory.file("jacoco/jvmTest.exec")
    )
}

// NUCLEAR FIX: Attempt to strip the 'archives' artifacts without triggering the warning
// by using the internal domain object container filtering.
configurations.configureEach {
    if (name == "archives") {
        description = "Deprecated configuration silenced for Gradle 10 compatibility"
        artifacts.clear()
    }
}

// Also disable the tasks to be safe
tasks.withType<Jar>().configureEach {
    if (name.contains("sourcesJar", ignoreCase = true) || 
        name.contains("javadocJar", ignoreCase = true) || 
        name.contains("dokka", ignoreCase = true)) {
        enabled = false
        group = null
    }
}
