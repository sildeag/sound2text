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
    // We use providers to avoid eager task realization
    val jvmTestTaskProvider = tasks.named<Test>("jvmTest")
    dependsOn(jvmTestTaskProvider)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    
    // Modern lazy property-based setup
    sourceDirectories.setFrom(
        project.layout.projectDirectory.dir("src/commonMain/kotlin"),
        project.layout.projectDirectory.dir("src/jvmMain/kotlin")
    )
    
    classDirectories.setFrom(
        project.fileTree(project.layout.buildDirectory.dir("classes/kotlin/jvm/main")) {
            exclude("**/commonTest/**", "**/jvmTest/**")
        }
    )
    
    executionData.setFrom(
        project.layout.buildDirectory.file("jacoco/jvmTest.exec")
    )
}

tasks.named("check") {
    dependsOn(jacocoTestReport)
}
