package com.sildeag.buildlogic.graph

import com.sildeag.buildlogic.DiagramFiles
import org.gradle.api.Project
import java.io.File
import kotlin.io.walkTopDown

object DiGraphExtractor {
    fun moduleGraph(project: Project): DiGraph {
        val settings = DiagramFiles.settingsFile(project)
        val graph = DiGraph("modules")
        if (!settings.exists()) return graph
        val text = settings.readText()
        val modules = Regex("""include\(":(.*?)"\)""")
            .findAll(text)
            .map { it.groupValues[1] }
            .toList()
        modules.forEach { module ->
            graph.addNode(id = module, label = module, group =
                "module")
            val buildFile = File(project.rootDir,
                "$module/build.gradle.kts")
            if (buildFile.exists()) {
                val bfText = buildFile.readText()
                Regex("""implementation\(project\(":(.*?)"\)\)""")
                    .findAll(bfText)
                    .forEach { match ->
                        val dep = match.groupValues[1]
                        graph.addNode(dep, dep, "module")
                        graph.addEdge(module, dep)
                    }
            }
        }
        return graph
    }
    fun diGraph(project: Project): DiGraph {
        val diDir = DiagramFiles.diDir(project)
        val graph = DiGraph("di")
        if (!diDir.exists()) return graph
        val bindingRegex = Regex("""(single|factory)\s*<\s*([A-Za-z0-9_.]+)\s*>""")
        val injectRegex = Regex("""get<\s*([A-Za-z0-9_.]+)\s*>""")
        diDir.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                val text = file.readText()
                bindingRegex.findAll(text).forEach { match ->
                    val type = match.groupValues[2]
                    val pkg = type.substringBeforeLast('.',
                        missingDelimiterValue = "root")
                    graph.addNode(type, type.substringAfterLast('.'),
                        pkg)
                    injectRegex.findAll(text).forEach { dep ->
                        val depType = dep.groupValues[1]
                        val depPkg = depType.substringBeforeLast('.',
                            missingDelimiterValue = "root")
                        graph.addNode(depType,
                            depType.substringAfterLast('.'), depPkg)
                        graph.addEdge(type, depType)
                    }
                }
            }
        return graph
    }
}
