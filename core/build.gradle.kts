plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    jacoco
}

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.logging)
                implementation(libs.kotlinx.serialization.json)
            }
        }

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
                    val module = it.module.toString()
                    val version = it.versionConstraint.requiredVersion
                    implementation("$module:$version:$platform")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
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

jacoco {
    toolVersion = "0.8.11"
}

// KMP: jvmTest exists, but is NOT a Test task, so no type parameter
tasks.named("jvmTest") {
    // no useJUnitPlatform() needed in KMP
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


//tasks.named<Test>("jvmTest") {
//    useJUnitPlatform()
//}
/*
jacoco {
    toolVersion = "0.8.11"
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.named("jvmTest"))
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    val mainSrc = files("src/commonMain/kotlin", "src/jvmMain/kotlin")
    sourceDirectories.setFrom(mainSrc)
    classDirectories.setFrom(files(layout.buildDirectory.dir("classes/kotlin/jvm/main")))
    executionData.setFrom(files(layout.buildDirectory.file("jacoco/jvmTest.exec")))
}

tasks.named<JacocoReport>("jacocoTestReport") {
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude(

                )
            }
        })
    )
}
*/