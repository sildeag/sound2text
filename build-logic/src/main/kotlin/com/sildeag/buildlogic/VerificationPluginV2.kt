package com.sildeag.buildlogic

import com.sildeag.buildlogic.tasks.GenerateAllGraphsV2Task
import com.sildeag.buildlogic.tasks.GenerateMermaidGraphsTask
import com.sildeag.buildlogic.tasks.GeneratePumlGraphsTask
import com.sildeag.buildlogic.verification.VerifyDiGraphTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import java.io.File
import java.net.URL

class VerificationPluginV2 : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("downloadPlantUml") {
            group = "verification"
            description = "Downloads plantuml.jar if missing"
            val jarFile = File(project.rootDir, "build-logic/plantuml/plantuml.jar")
            doLast {
                if (!jarFile.exists()) {
                    jarFile.parentFile.mkdirs()
                    println("Downloading PlantUML...")

                    // Create a URI first, then convert it to a URL
                    java.net.URI("https://github.com/plantuml/plantuml/releases/download/v1.2024.7/plantuml-1.2024.7.jar")
                        .toURL()
                        .openStream()
                        .use { input ->
                            jarFile.outputStream().use { output ->
                                input.copyTo(output)
                            }
                        }
                    println("PlantUML downloaded to ${jarFile.absolutePath}")
                }
            }
        }

        project.tasks.register("verifyDiGraph", VerifyDiGraphTask::class.java)

        project.tasks.register("generateMermaidGraphsV2", GenerateMermaidGraphsTask::class)
        project.tasks.register("generatePumlGraphsV2", GeneratePumlGraphsTask::class) {
            dependsOn("downloadPlantUml")
        }
        project.tasks.register("generateAllGraphsV2", GenerateAllGraphsV2Task::class) {
            dependsOn("generateMermaidGraphsV2", "generatePumlGraphsV2")
        }
    }
}
