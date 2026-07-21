plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.openjfx.javafxplugin")
    id("org.jetbrains.kotlin.plugin.compose")
    id("jacoco")
    id("internal.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// JavaFX configuration
javafx {
    version = libs.findVersion("javafx").get().requiredVersion
    modules("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.swing")
}

// Ensure the JVM target matches your project (e.g., JVM 24 or 21)
kotlin {
    jvmToolchain(libs.findVersion("jvm").get().requiredVersion.toInt())
}

dependencies {
    implementation(libs.findLibrary("vosk-api").get())
    implementation(libs.findBundle("itext").get())
    implementation(libs.findBundle("logging").get())
    implementation(libs.findLibrary("kotlinx-serialization-json").get())
    
    testImplementation(libs.findBundle("testJvm").get())
}

// Jacoco setup
jacoco {
    toolVersion = libs.findVersion("jacoco-ver").get().requiredVersion
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    
    // Ensure lazy configuration for classes and sources
    sourceDirectories.setFrom(project.layout.projectDirectory.dir("src/main/kotlin"))
    classDirectories.setFrom(
        project.fileTree(project.layout.buildDirectory.dir("classes/kotlin/main"))
    )
    executionData.setFrom(
        project.layout.buildDirectory.file("jacoco/test.exec")
    )
}
