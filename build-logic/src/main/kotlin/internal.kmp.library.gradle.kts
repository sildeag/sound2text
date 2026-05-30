import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("internal.kmp.android.library")
    id("internal.kmp.jvm.library")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Explicitly target the Kotlin Multiplatform Extension to avoid DSL ambiguity
configure<KotlinMultiplatformExtension> {
    // In AGP 9.1+, 'androidLibrary' is renamed back to 'android' within 'kotlin'
    @Suppress("OPT_IN_USAGE")
    android {
        namespace = project.group.toString()
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        // In AGP 9.1+, host-side unit tests are enabled and configured via withHostTest
        //withHostTest {
            // Configuration for host-side unit tests
        //}

        // On-device tests are enabled and configured via withDeviceTest
        //withDeviceTest {
            // Configuration for device-side unit tests
        //}

        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(libs.findVersion("jvm").get().requiredVersion))
        }
    }
}

afterEvaluate {
    extensions.findByType<org.jetbrains.dokka.gradle.DokkaExtension>()?.apply {
        dokkaSourceSets.configureEach {
            includes.from(project.layout.projectDirectory.file("DEVLOG.md"))
        }
    }
}
