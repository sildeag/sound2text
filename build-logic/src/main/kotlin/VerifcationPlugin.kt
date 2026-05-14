package com.sildeag.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.process.ExecOperations
import org.gradle.kotlin.dsl.*
import org.gradle.kotlin.dsl.register
import org.gradle.process.ExecSpec
import java.io.File
import kotlin.io.walkTopDown

class VerificationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        registerCheckUnusedDiTask(project)
        registerGenerateModuleGraphTask(project)
        registerGenerateDiGraphTask(project)
        registerGenerateAllGraphsTask(project)
        registerRenderMermaidGraphsTask(project)
        registerValidateDiTask(project)
        // Optional: registerWatchDiTask(project)
        registerDiscoveredScripts(project)
    }

    // ---------- Color helpers ----------

    private val RESET = "\u001B[0m"
    private val RED = "\u001B[31m"
    private val GREEN = "\u001B[32m"
    private val YELLOW = "\u001B[33m"

    private fun ok(msg: String) = println("$GREEN$msg$RESET")
    private fun warn(msg: String) = println("$YELLOW$msg$RESET")
    private fun err(msg: String) = println("$RED$msg$RESET")

    // ---------- Script metadata ----------

    data class ScriptMetadata(
        val description: String = "",
        val group: String = "verification",
        val dependsOn: List<String> = emptyList()
    )

    private fun parseMetadata(file: File): ScriptMetadata {
        val lines = file.readLines().take(10)
        var description = ""
        var group = "verification"
        var dependsOn = emptyList<String>()

        lines.forEach { line ->
            when {
                line.startsWith("// @description:") ->
                    description = line.removePrefix("// @description:").trim()

                line.startsWith("// @group:") ->
                    group = line.removePrefix("// @group:").trim()

                line.startsWith("// @dependsOn:") ->
                    dependsOn = line.removePrefix("// @dependsOn:")
                        .split(",")
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
            }
        }

        return ScriptMetadata(description, group, dependsOn)
    }

    // ---------- checkUnusedDi ----------

    private fun registerCheckUnusedDiTask(targetProject: Project) {
        targetProject.tasks.register("checkUnusedDi") {
            group = "verification"
            description = "Checks for unused DI bindings across the project"

            doLast {
                val root = targetProject.rootDir
                val diSrc = File(root, "di/src")

                if (!diSrc.exists()) {
                    warn("DI source directory not found at ${diSrc.absolutePath}")
                    return@doLast
                }

                val diFiles = diSrc.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .toList()

                val allFiles = root.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .filter { !it.absolutePath.contains("${File.separator}build${File.separator}") }
                    .toList()

                val bindingRegex = Regex("""(single|factory)\s*<\s*([A-Za-z0-9_.]+)\s*>""")
                val bindings = mutableSetOf<String>()

                diFiles.forEach { file ->
                    bindingRegex.findAll(file.readText()).forEach { match ->
                        bindings.add(match.groupValues[2])
                    }
                }

                println("--- DI Usage Check ---")
                var unusedCount = 0

                bindings.forEach { type ->
                    val usedOutsideDi = allFiles.any { file ->
                        !file.absolutePath.startsWith(diSrc.absolutePath) &&
                                file.readText().contains(type)
                    }
                    if (!usedOutsideDi) {
                        warn("[!] Possibly unused: $type")
                        unusedCount++
                    }
                }

                if (unusedCount == 0) {
                    ok("All bindings appear used.")
                } else {
                    warn("Found $unusedCount possibly unused binding(s).")
                }
                println("----------------------")
            }
        }
    }

    // ---------- Module graph ----------

    private fun registerGenerateModuleGraphTask(targetProject: Project) {
        targetProject.tasks.register("generateModuleGraph") {
            group = "verification"
            description = "Generates a Mermaid diagram of module dependencies"

            doLast {
                val settings = File(targetProject.rootDir, "settings.gradle.kts")
                if (!settings.exists()) {
                    err("settings.gradle.kts not found")
                    return@doLast
                }

                val text = settings.readText()
                val modules = Regex("""include\(":(.*?)"\)""")
                    .findAll(text)
                    .map { it.groupValues[1] }
                    .toList()

                val sb = StringBuilder()
                sb.appendLine("graph TD")

                modules.forEach { module ->
                    val buildFile = File(targetProject.rootDir, "$module/build.gradle.kts")
                    if (buildFile.exists()) {
                        val bfText = buildFile.readText()
                        Regex("""implementation\(project\(":(.*?)"\)\)""")
                            .findAll(bfText)
                            .forEach { match ->
                                val dep = match.groupValues[1]
                                sb.appendLine("    $module --> $dep")
                            }
                    }
                }

                val out = File(targetProject.rootDir, "module-graph.mmd")
                out.writeText(sb.toString())
                ok("Generated module-graph.mmd")
            }
        }
    }

    // ---------- DI graph ----------

    private fun registerGenerateDiGraphTask(targetProject: Project) {
        targetProject.tasks.register("generateDiGraph") {
            group = "verification"
            description = "Generates a Mermaid diagram of DI bindings"

            doLast {
                val diDir = File(targetProject.rootDir, "di/src")
                if (!diDir.exists()) {
                    err("DI directory not found at ${diDir.absolutePath}")
                    return@doLast
                }

                val sb = StringBuilder()
                sb.appendLine("graph TD")

                val bindingRegex = Regex("""(single|factory)\s*<\s*([A-Za-z0-9_.]+)\s*>""")
                val injectRegex = Regex("""get<\s*([A-Za-z0-9_.]+)\s*>""")

                diDir.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .forEach { file ->
                        val text = file.readText()

                        bindingRegex.findAll(text).forEach { match ->
                            val from = match.groupValues[2]

                            injectRegex.findAll(text).forEach { dep ->
                                val to = dep.groupValues[1]
                                sb.appendLine("    $from --> $to")
                            }
                        }
                    }

                val out = File(targetProject.rootDir, "di-graph.mmd")
                out.writeText(sb.toString())
                ok("Generated di-graph.mmd")
            }
        }
    }

    // ---------- Combined graphs ----------

    private fun registerGenerateAllGraphsTask(targetProject: Project) {
        targetProject.tasks.register("generateAllGraphs") {
            group = "verification"
            description = "Generates all Mermaid graphs (modules, DI)"

            dependsOn(
                "generateModuleGraph",
                "generateDiGraph"
            )
        }
    }

    // ---------- Render Mermaid to PNG/SVG ----------

    private fun registerRenderMermaidGraphsTask(targetProject: Project) {
        targetProject.tasks.register("renderMermaidGraphs") {
            group = "verification"
            description = "Renders all Mermaid .mmd files to PNG and SVG using mmdc"

            doLast {
                val root = targetProject.rootDir
                val mermaidFiles = root.walkTopDown()
                    .filter { it.isFile && it.extension == "mmd" }
                    .toList()

                if (mermaidFiles.isEmpty()) {
                    warn("No .mmd files found to render.")
                    return@doLast
                }

                mermaidFiles.forEach { file ->
                    val base = file.absolutePath.removeSuffix(".mmd")

                    try {
                        targetProject.objects.newInstance(ExecOperations::class.java).exec {
                            commandLine("mmdc", "-i", file.absolutePath, "-o", "${base}.png")
                        }
                        targetProject.objects.newInstance(ExecOperations::class.java).exec {
                            commandLine("mmdc", "-i", file.absolutePath, "-o", "${base}.svg")
                        }
                        ok("Rendered: ${file.name} → PNG/SVG")
                    } catch (e: Exception) {
                        err("Failed to render ${file.name}: ${e.message}")
                    }
                }
            }
        }
    }

    // ---------- DI validation ----------

    private fun registerValidateDiTask(targetProject: Project) {
        targetProject.tasks.register("validateDi") {
            group = "verification"
            description = "Validates DI bindings (missing, duplicate, circular)"

            doLast {
                val diDir = File(targetProject.rootDir, "di/src")
                if (!diDir.exists()) {
                    err("DI directory not found at ${diDir.absolutePath}")
                    return@doLast
                }

                val bindingRegex = Regex("""(single|factory)\s*<\s*([A-Za-z0-9_.]+)\s*>""")
                val bindings = mutableMapOf<String, MutableList<File>>()

                diDir.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .forEach { file ->
                        bindingRegex.findAll(file.readText()).forEach { match ->
                            val type = match.groupValues[2]
                            bindings.computeIfAbsent(type) { mutableListOf() }.add(file)
                        }
                    }

                println("--- DI Validation ---")

                // Duplicates
                bindings.filter { it.value.size > 1 }.forEach { (type, files) ->
                    warn("[!] Duplicate binding for $type:")
                    files.forEach { println("    - ${it.path}") }
                }

                // Missing bindings
                val allFiles = targetProject.rootDir.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .toList()

                val referencedTypes = mutableSetOf<String>()
                val typeRegex = Regex("""get<\s*([A-Za-z0-9_.]+)\s*>""")

                allFiles.forEach { file ->
                    typeRegex.findAll(file.readText()).forEach { match ->
                        referencedTypes.add(match.groupValues[1])
                    }
                }

                referencedTypes.filter { it !in bindings.keys }.forEach { missing ->
                    warn("[!] Missing DI binding for: $missing")
                }

                // Simple circular detection
                val edges = mutableListOf<Pair<String, String>>()
                val injectRegex = Regex("""get<\s*([A-Za-z0-9_.]+)\s*>""")

                diDir.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .forEach { file ->
                        val text = file.readText()
                        bindingRegex.findAll(text).forEach { match ->
                            val from = match.groupValues[2]
                            injectRegex.findAll(text).forEach { dep ->
                                val to = dep.groupValues[1]
                                edges.add(from to to)
                            }
                        }
                    }

                val cycles = edges.filter { (a, b) -> edges.contains(b to a) }
                cycles.forEach { (a, b) ->
                    err("[!] Circular DI dependency: $a ↔ $b")
                }

                ok("DI validation complete.")
                println("----------------------")
            }
        }
    }

    // ---------- Optional DI watcher ----------

    @Suppress("unused")
    private fun registerWatchDiTask(targetProject: Project) {
        targetProject.tasks.register("watchDi") {
            group = "verification"
            description = "Watches DI files and regenerates DI graph on change"

            inputs.files(targetProject.fileTree("di/src"))
            outputs.file(File(targetProject.rootDir, "di-graph.mmd"))

            doLast {
                targetProject.tasks.named("generateDiGraph").get().actions.forEach { action ->
                    action.execute(this)
                }
                ok("DI graph regenerated from watchDi.")
            }
        }
    }

    // ---------- Auto-discovered scripts ----------

    private fun registerDiscoveredScripts(targetProject: Project) {
        val scriptsDir = File(targetProject.rootDir, "scripts")
        if (!scriptsDir.exists()) return

        scriptsDir.walkTopDown()
            .filter { it.isFile && it.extension == "kts" }
            .forEach { scriptFile ->
                val meta = parseMetadata(scriptFile)
                val taskName = "script_" + scriptFile.nameWithoutExtension

                targetProject.tasks.register(taskName) {
                    group = meta.group
                    description = if (meta.description.isNotBlank())
                        meta.description
                    else
                        "Runs script: ${scriptFile.name}"

                    meta.dependsOn.forEach { dep ->
                        dependsOn(dep)
                    }

                    doLast {
                        try {
                            targetProject.objects.newInstance(ExecOperations::class.java).exec {
                                commandLine("kotlin", scriptFile.absolutePath)
                            }
                            ok("Script ${scriptFile.name} completed successfully.")
                        } catch (e: Exception) {
                            err("Script ${scriptFile.name} failed: ${e.message}")
                        }
                    }
                }
            }
    }
}
