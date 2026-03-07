plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    jacoco

}

val platform = "win"

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //implementation(project(":core"))
                implementation(project(":config"))

                implementation(libs.compose.mpp.runtime)
                implementation(libs.compose.mpp.foundation)
                implementation(libs.compose.mpp.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.compose.mpp.runtime)
                implementation(libs.compose.mpp.foundation)
                implementation(libs.compose.mpp.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(project(":config"))
                //implementation(project(":config"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
                implementation(libs.bundles.logging)

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
                implementation(libs.bundles.testJvm)
                implementation(kotlin("test"))
            }
        }
    }
}

dokka {
    moduleName.set(":ui-common")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

jacoco {
    toolVersion = "0.8.11"
}

// Root Jacoco aggregator for all subprojects
tasks.register("jacocoRootReport") {
    group = "verification"
    description = "Aggregates Jacoco reports from all subprojects."
    // Collect all subproject Jacoco tasks named 'jacocoTestReport'
    val jacocoTasks = subprojects.mapNotNull { sub ->
        sub.tasks.findByName("jacocoTestReport")
    }
    dependsOn(jacocoTasks)
    doLast {
        println("Jacoco reports generated for: " +
                jacocoTasks.joinToString { it.project.name })
    }
}