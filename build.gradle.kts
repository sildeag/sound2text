plugins {
    // ANDROID PLUGINS
    // These provide the Android Gradle Plugin (AGP) for app + library modules.
    // android-ui will use androidApplication; any future Android libs use androidLibrary.
    //
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
    //
    // KOTLIN LANGUAGE PLUGINS
    // Each module picks the plugin that matches its role:
    // - android-ui → kotlinAndroid
    // - desktop-ui → kotlinMultiplatform  // kotlinJvm is not used
    // - core → kotlinMultiplatform
    //
    alias(libs.plugins.kotlinAndroid) apply false
    //alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    //
    // COMPOSE PLUGINS
    // composeCompiler → Kotlin Compose compiler plugin (K2), used by Android + MPP UI
    // composeMultiplatformDesktop → Compose Desktop plugin (1.9.x), used by desktop-ui (and optionally core)
    //
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    //
    // TOOLING PLUGINS
    // Optional per-module: KSP for codegen, Dokka for documentation.
    //
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dokka) apply false
}

tasks.register("dokkaAll") {
    dependsOn(
        subprojects.mapNotNull { sub ->
            sub.tasks.findByName("dokkaGenerateModule")
        }
    )
}