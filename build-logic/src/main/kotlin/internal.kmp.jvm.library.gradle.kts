import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    // 1. Extend the base KMP configuration (which handles jvmToolchain, common dependencies, etc.)
    id("internal.kmp.base")

    // 2. Add documentation support (Dokka) for library modules
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Explicitly configure the Kotlin Multiplatform Extension
configure<KotlinMultiplatformExtension> {
    // We ONLY call jvm() here.
    // We DO NOT call androidTarget() or any other platform.
    jvm()

    sourceSets {
        // commonMain is already configured in internal.kmp.base

        jvmMain {
            // This is where you put your Desktop-specific logic (Vosk, iText)
            dependencies {
                // You can add common desktop-only libraries here if needed
            }
        }

        jvmTest {
            dependencies {
                // Standard JVM testing bundle
                implementation(libs.findBundle("testJvm").get())
            }
        }
    }
}

// Optional: Add Dokka specific documentation config for these libraries
afterEvaluate {
    extensions.findByType<org.jetbrains.dokka.gradle.DokkaExtension>()?.apply {
        dokkaSourceSets.configureEach {
            includes.from(project.layout.projectDirectory.file("DEVLOG.md"))
        }
    }
}