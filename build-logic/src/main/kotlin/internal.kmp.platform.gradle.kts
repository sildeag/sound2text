import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")                     // shared KMP setup
    id("com.android.kotlin.multiplatform.library") // ONLY platform modules need this
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    // Platform module needs JVM + Android source sets
    jvmToolchain(libs.findVersion("jvm").get().requiredVersion.toInt())

    @Suppress("OPT_IN_USAGE")
    android {
        namespace = "${project.group}.platform"
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        withHostTest {}
        // No withDeviceTest

        compilerOptions {
            jvmTarget.set(
                org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(
                    libs.findVersion("jvm").get().requiredVersion
                )
            )
        }
    }

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                // Platform module stays clean — no UI, no Compose, no AndroidX
            }
        }

        val androidMain = getByName("androidMain") {
            dependencies {
                // Android-specific platform implementations go here
            }
        }

        val jvmMain = getByName("jvmMain") {
            dependencies {
                // Desktop-specific platform implementations go here
            }
        }
    }

    compilerOptions {
        @Suppress("OPT_IN_USAGE")
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

afterEvaluate {
    extensions.findByType<org.jetbrains.dokka.gradle.DokkaExtension>()?.apply {
        dokkaSourceSets.configureEach {
            includes.from(project.layout.projectDirectory.file("DEVLOG.md"))
        }
    }
}
