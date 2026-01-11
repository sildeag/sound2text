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

kotlin {
    // JVM-only KMP module
    jvmToolchain(17)
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":config"))
                implementation(project(":core"))

                // Compose MPP (NOT Compose Desktop)
                implementation(libs.compose.mpp.runtime)
                implementation(libs.compose.mpp.foundation)
                implementation(libs.compose.mpp.material3)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(libs.bundles.testJvm)
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":config"))
                implementation(project(":core"))

                // Compose MPP (NOT Compose Desktop)
                implementation(libs.compose.mpp.runtime)
                implementation(libs.compose.mpp.foundation)
                implementation(libs.compose.mpp.material3)

                // Shared libs
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)

                // JVM-specific
                implementation(libs.bundles.itext)
                implementation(libs.vosk.api)
                implementation(libs.snakeyaml)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":core"))
                implementation(libs.bundles.testJvm)
                implementation(kotlin("test"))
            }
        }
    }
}

dokka {
    moduleName.set(":core-ui")
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

    val mainSrc = files("src/jvmMain/kotlin")
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
