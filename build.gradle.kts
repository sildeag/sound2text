plugins {
    // Android
    alias(libs.plugins.androidKmp) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false

    // Kotlin
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.javafx) apply false

    // Compose
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false

    // Tooling
    //alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dokka) apply false // Re-enabled for AGP 9.1+
    jacoco
    id("internal.verification")
    id("internal.verification.v2")
}

// Global Dokka task
tasks.register("dokkaAll") {
    dependsOn(
        subprojects.mapNotNull { sub ->
            sub.tasks.findByName("dokkaGenerateModule")
        }
    )
}

// Custom/Draft Scripts
if (File("scripts/draft-tasks.gradle.kts").exists()) {
    apply(from = "scripts/draft-tasks.gradle.kts")
}


