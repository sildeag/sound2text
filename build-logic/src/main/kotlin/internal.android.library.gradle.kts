import org.jetbrains.dokka.gradle.DokkaExtension

plugins {
    id("com.android.library")
    id("internal.dokka")
    id("jacoco")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    namespace = project.group.toString()
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.findVersion("jvm").get().requiredVersion)
        targetCompatibility = JavaVersion.toVersion(libs.findVersion("jvm").get().requiredVersion)
    }
}