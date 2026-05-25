package com.sildeag.buildlogic.tasks

import com.sildeag.buildlogic.DiagramFiles
import com.sildeag.buildlogic.graph.DiGraphExtractor
import com.sildeag.buildlogic.plantuml.PumlTemplate
import com.sildeag.buildlogic.plantuml.PlantUmlRenderer
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import javax.inject.Inject
import java.io.File
abstract class GeneratePumlGraphsTask @Inject constructor(
    private val execOps: ExecOperations
) : DefaultTask() {
    @TaskAction
    fun generate() {
        val outDir = DiagramFiles.outputDir(project, "puml")
        val template = PumlTemplate()
        val renderer = PlantUmlRenderer(execOps)
        val jar = File(project.rootDir,
            "build-logic/plantuml/plantuml.jar")
        if (!jar.exists()) {
            println("plantuml.jar not found at ${jar.absolutePath}")
            return
        }
        val moduleGraph = DiGraphExtractor.moduleGraph(project)
        val moduleFile = File(outDir, "module-graph.puml")
        moduleFile.writeText(template.render(moduleGraph))
        renderer.render(moduleFile, jar)
        val diGraph = DiGraphExtractor.diGraph(project)
        val diFile = File(outDir, "di-graph.puml")
        diFile.writeText(template.render(diGraph))
        renderer.render(diFile, jar)
    }
}
