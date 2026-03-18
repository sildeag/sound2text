import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    // Extend base KMP setup
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    // Add Android Library target to the base JVM/Common setup
    androidLibrary {
        namespace = project.group.toString()
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }

    /* 
       UPGRADE NOTE (AGP 9.0.1+ Stable): 
       When upgrading to AGP 9.0.1 or higher:
       1. Add the 'withHostTest' block inside androidLibrary for host-side unit tests.
       2. Re-enable Dokka.
    */
}
