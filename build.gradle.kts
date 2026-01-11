plugins {
    // Android
    alias(libs.plugins.androidKmp) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false

    // Kotlin
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false

    // Compose
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false

    // Tooling
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dokka) apply false
    jacoco
}

tasks.register("dokkaAll") {
    dependsOn(
        subprojects.mapNotNull { sub ->
            sub.tasks.findByName("dokkaGenerateModule")
        }
    )
}
