plugins {
    `kotlin-dsl`
    //kotlin("jvm") version "2.4.0"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.serialization.gradle.plugin)
    implementation(libs.compose.gradle.plugin)
    implementation(libs.compose.compiler.gradle.plugin)
    //implementation(libs.ksp.gradle.plugin)
    //implementation(libs.room.gradle.plugin)
    implementation(libs.dokka.gradle.plugin) // Re-enabled for AGP 9.1+ compatibility
    implementation(libs.javafx.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("verificationV2") {
            id = "internal.verification.v2"
            implementationClass = "com.sildeag.buildlogic.VerificationPluginV2"
        }
    }
}
kotlin {
    //jvmToolchain(8)
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }
}
