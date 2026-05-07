package com.sildeag.buildlogic.tasks

import com.sildeag.buildlogic.DiagramFiles
import com.sildeag.buildlogic.graph.DiGraphExtractor
import com.sildeag.buildlogic.mermaid.MermaidRenderer
import com.sildeag.buildlogic.mermaid.MermaidTemplate
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import javax.inject.Inject
import java.io.File
abstract class GenerateMermaidGraphsTask @Inject constructor(
    private val execOps: ExecOperations
) : DefaultTask() {
    @TaskAction
    fun generate() {
        val outDir = DiagramFiles.outputDir(project, "mermaid")
        val template = MermaidTemplate()
        val renderer = MermaidRenderer(execOps)
        val moduleGraph = DiGraphExtractor.moduleGraph(project)
        val moduleFile = File(outDir, "module-graph.mmd")
        moduleFile.writeText(template.render(moduleGraph))
        renderer.render(moduleFile)
        val diGraph = DiGraphExtractor.diGraph(project)
        val diFile = File(outDir, "di-graph.mmd")
        diFile.writeText(template.render(diGraph))
        renderer.render(diFile)
    }
}
