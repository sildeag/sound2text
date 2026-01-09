//import org.gradle.kotlin.dsl.implementation
//import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    //alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)   // Core KMP plugin
    //alias(libs.plugins.composeCompiler)      // Kotlin Compose compiler plugin (matches Kotlin version)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp) // KSP plugin if you use annotation processing in core
    alias(libs.plugins.dokka)
}

kotlin {

    jvmToolchain(17)
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //implementation(libs.compose.mpp.runtime)
                //implementation(libs.compose.mpp.foundation)
                //implementation(libs.compose.mpp.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                //implementation(libs.compose.mpp.runtime)
                //implementation(libs.compose.mpp.foundation)
                //implementation(libs.compose.mpp.material3)
                implementation(kotlin("test"))
                // the above is same as implementation(libs.kotlin.test)
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }




        /*jvm("jvm") {
            compilations["main"].defaultSourceSet {
            /*java.srcDirs("src/jvmMain/java")*/
            kotlin.srcDirs("src/jvmMain/kotlin")
            resources.srcDirs("src/jvmMain/resources")
            }
        }*/

        val platform = "win"
        val jvmMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.snakeyaml)
                implementation(libs.kotlinx.serialization.json)
                libs.bundles.javafx.get().forEach {
                    val module = it.module.toString() // e.g., "org.openjfx:javafx-base"

                    val version = it.versionConstraint.requiredVersion // e.g., "20"
                    implementation("$module:$version:$platform")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
                //testImplementation(kotlin("test"))
                //testImplementation(libs.junit.jupiter.api)
                //testRuntimeOnly(libs.junit.jupiter.engine)
                //implementation(libs.mockk)
                //implementation(libs.koin.test.junit5)
                implementation(libs.bundles.testJvm)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
                implementation(libs.snakeyaml)
                implementation(libs.kotlinx.serialization.json)
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
