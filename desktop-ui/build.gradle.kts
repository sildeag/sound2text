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

val platform = "win"

kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {
        val jvmMain by getting {
            resources.srcDir("src/jvmMain/resources")
            dependencies {
                implementation(project(":core"))
                implementation(project(":ui-shared"))
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.vosk)
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
                implementation(compose.desktop.currentOs)
                implementation(libs.bundles.testJvm)
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
    moduleName.set(":desktop-ui")
    dokkaSourceSets.configureEach {
        includes.from("DEVLOG.md")
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.named("jvmTest"))
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    val mainSrc = files("src/jvmMain/kotlin")
    sourceDirectories.setFrom(mainSrc)
    classDirectories.setFrom(files(layout.buildDirectory.dir("classes/kotlin/jvm/main")))
    executionData.setFrom(files(layout.buildDirectory.file("jacoco/jvmTest.exec")))
}

tasks.named<JacocoReport>("jacocoTestReport") {
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude(
                    "**/commonTest/**",
                    "**/jvmTest/**"
                )
            }
        })
    )
}
