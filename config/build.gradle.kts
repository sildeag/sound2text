plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    jacoco
}

kotlin {
    jvmToolchain(17)
    jvm()
    
    androidLibrary {
        namespace = "com.sildeag.sound2text.config"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        withHostTest {}
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.bundles.logging)
                implementation(libs.koin.core)
                implementation(libs.bundles.coroutines)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.bundles.testJvm)
            }
        }
        jvmMain {
            dependencies {
                // JVM specific if any
            }
        }
        jvmTest {
            dependencies {
                // JVM specific tests
            }
        }
        val androidMain by getting {
            dependencies {
                // Android specific if any
            }
        }
        val androidHostTest by getting {
            dependencies {
                // Android specific if any
            }
        }
    }
}

dokka {
    moduleName.set(":config")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Jacoco report configuration for JVM target
val jacocoTestReport by tasks.registering(JacocoReport::class) {
    dependsOn("jvmTest")
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    
    val mainSrc = files("src/commonMain/kotlin", "src/jvmMain/kotlin")
    sourceDirectories.setFrom(mainSrc)
    
    classDirectories.setFrom(
        fileTree(layout.buildDirectory.dir("classes/kotlin/jvm/main")) {
            exclude("**/commonTest/**", "**/jvmTest/**")
        }
    )
    
    executionData.setFrom(
        files(layout.buildDirectory.file("jacoco/jvmTest.exec"))
    )
}
