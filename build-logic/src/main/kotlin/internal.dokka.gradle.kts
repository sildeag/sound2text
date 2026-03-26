plugins {
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Centralized Dokka Configuration
// We use afterEvaluate to ensure all source sets from KMP/Android are fully loaded
afterEvaluate {
    extensions.findByType<org.jetbrains.dokka.gradle.DokkaExtension>()?.apply {
        // Shared settings for all modules
        dokkaSourceSets.configureEach {
            // Include your development log in the documentation
            val devLog = project.layout.projectDirectory.file("DEVLOG.md")
            if (devLog.asFile.exists()) {
                includes.from(devLog)
            }

            // Example: Link to Kotlin standard library documentation
            reportUndocumented.set(false)
            skipEmptyPackages.set(true)
        }
        
        // Example: Global footer or logo settings
        // pluginsConfiguration.html {
        //     footerMessage.set("Copyright © 2025 Sildeag Sound2Text")
        // }
    }
}
