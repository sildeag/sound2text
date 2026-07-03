import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("internal.dokka")
    id("jacoco")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain(libs.findVersion("jvm").get().requiredVersion.toInt())

    // Modern KMP Android Library target (AGP 9.1+)
    @Suppress("OPT_IN_USAGE")
    android {
        namespace = "${project.group}.${project.name.replace("-", "")}"
        //namespace = project.group.toString()
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        
        withHostTest { }
        withDeviceTest { }
    }
}

// Jacoco setup
jacoco {
    toolVersion = libs.findVersion("jacoco-ver").get().requiredVersion
}
