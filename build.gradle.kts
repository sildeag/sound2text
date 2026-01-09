plugins {
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
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
