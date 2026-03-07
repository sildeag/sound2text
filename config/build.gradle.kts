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
    
    // Suppress warning about expect/actual classes being in Beta
    @Suppress("OPT_IN_USAGE")
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

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
    toolVersion = libs.versions.jacoco.ver.get()
}

tasks.withType<Test> {
    useJUnitPlatform()
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
            exclude("**/commonTest/**", "**/jvmTest/**")
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
