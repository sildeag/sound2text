import org.jetbrains.compose.desktop.application.dsl.TargetFormat.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
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

    // Suppress warning about expect/actual classes being in Beta
    @Suppress("OPT_IN_USAGE")
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(project(":config"))
                implementation(project(":ui-common"))
                implementation(project(":stt"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(project(":config"))
                implementation(project(":ui-common"))
                implementation(project(":stt"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
            }
        }

        val jvmMain by getting {
            resources.srcDir("src/jvmMain/resources")
            dependencies {
                implementation(project(":core"))
                implementation(project(":config"))
                implementation(project(":ui-common"))
                implementation(project(":stt"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.logging)
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
                implementation(project(":core"))
                implementation(project(":ui-common"))
                implementation(compose.desktop.currentOs)
                implementation(libs.bundles.testJvm)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.bundles.logging)

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
    moduleName.set(":ui-desktop")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

jacoco {
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

// NUCLEAR FIX: Attempt to silence 'archives' deprecation warning
configurations.configureEach {
    if (name == "archives") {
        description = "Deprecated configuration silenced for Gradle 10 compatibility"
        artifacts.clear()
    }
}

// Disable tasks that are known to use the deprecated 'archives' configuration.
tasks.withType<Jar>().configureEach {
    if (name.contains("sourcesJar", ignoreCase = true) || 
        name.contains("javadocJar", ignoreCase = true) || 
        name.contains("dokka", ignoreCase = true)) {
        enabled = false
        group = null
    }
}
