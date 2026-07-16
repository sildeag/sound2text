import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    //id("com.google.devtools.ksp")
    id("jacoco")
    id("internal.dokka") // Applied here to cover all KMP modules
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain(libs.findVersion("jvm").get().requiredVersion.toInt())
    jvm()

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                implementation(libs.findLibrary("koin-core").get())
                implementation(libs.findBundle("coroutines").get())
                implementation(libs.findBundle("logging").get())
                implementation(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
        val commonTest = getByName("commonTest") {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    compilerOptions {
        @Suppress("OPT_IN_USAGE")
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

// Common Jacoco setup
jacoco {
    toolVersion = libs.findVersion("jacoco-ver").get().requiredVersion
}

val jacocoTestReport = tasks.register<JacocoReport>("jacocoKmpTestReport") {
    val jvmTestTask = tasks.named<Test>("jvmTest")
    dependsOn(jvmTestTask)

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
    executionData.setFrom(layout.buildDirectory.file("jacoco/jvmTest.exec"))
}

tasks.named("check") {
    dependsOn(jacocoTestReport)
}
