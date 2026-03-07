import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    jacoco
}

// NOTE: This module is intended to be merged into :ui-common.
// It currently serves as the shared UI core logic and theme layer.

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
                implementation(project(":config"))
                implementation(project(":core"))

                // Compose Multiplatform (CMP) - Inherited by all targets
                implementation(libs.compose.mpp.runtime)
                implementation(libs.compose.mpp.foundation)
                implementation(libs.compose.mpp.material3)
                
                // Shared logic libs
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        
        val commonTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(kotlin("test"))
            }
        }
        
        val jvmMain by getting {
            dependencies {
                // JVM-specific implementations
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.snakeyaml)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.bundles.testJvm)
                implementation(kotlin("test"))
            }
        }
    }
}

dokka {
    moduleName.set(":ui-core")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

jacoco {
    toolVersion = libs.versions.jacoco.ver.get()
}

// Modern Jacoco configuration (Gradle 10 compatible)
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
