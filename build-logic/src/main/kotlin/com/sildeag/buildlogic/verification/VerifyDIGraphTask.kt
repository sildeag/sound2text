package com.sildeag.buildlogic.verification

import com.sildeag.buildlogic.graph.DiGraphExtractor
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

abstract class VerifyDiGraphTask : DefaultTask() {

    @TaskAction
    fun verify() {
        val graph = DiGraphExtractor.diGraph(project)

        val errors = mutableListOf<String>()

        // --- Duplicate edges ---
        val duplicateEdges = graph.edges
            .groupBy { it }
            .filter { (_, list) -> list.size > 1 }

        if (duplicateEdges.isNotEmpty()) {
            errors += "Duplicate DI edges detected:"
            duplicateEdges.forEach { (edge, list) ->
                errors += "  ${edge.from} -> ${edge.to} (${list.size} duplicates)"
            }
        }

        // --- Self edges ---
        val selfEdges = graph.edges.filter { it.from == it.to }
        if (selfEdges.isNotEmpty()) {
            errors += "Self-referencing DI edges detected:"
            selfEdges.forEach { edge ->
                errors += "  ${edge.from} -> ${edge.to}"
            }
        }

        // --- Orphan nodes ---
        val referenced = graph.edges.flatMap { listOf(it.from, it.to) }.toSet()
        val orphanNodes = graph.nodes.map { it.id }.filter { it !in referenced }

        if (orphanNodes.isNotEmpty()) {
            errors += "Orphan DI nodes (never used in any binding):"
            orphanNodes.forEach { id ->
                errors += "  $id"
            }
        }

        // --- Circular dependencies ---
        val cycles = findCycles(graph)
        if (cycles.isNotEmpty()) {
            errors += "Circular DI dependencies detected:"
            cycles.forEach { cycle ->
                errors += "  ${cycle.joinToString(" -> ")}"
            }
        }

        // --- Final result ---
        if (errors.isNotEmpty()) {
            logger.error("DI verification failed:")
            errors.forEach { logger.error(it) }
            throw GradleException("DI graph verification failed")
        }

        logger.lifecycle("DI graph verified successfully")
    }

    private fun findCycles(graph: com.sildeag.buildlogic.graph.DiGraph): List<List<String>> {
        val cycles = mutableListOf<List<String>>()
        val adjacency = graph.edges.groupBy({ it.from }, { it.to })

        fun dfs(path: List<String>, visited: Set<String>) {
            val current = path.last()
            val nextNodes = adjacency[current] ?: return

            for (next in nextNodes) {
                if (next in visited) {
                    val cycleStart = path.indexOf(next)
                    cycles += path.subList(cycleStart, path.size) + next
                } else {
                    dfs(path + next, visited + next)
                }
            }
        }

        graph.nodes.forEach { node ->
            dfs(listOf(node.id), setOf(node.id))
        }

        return cycles
    }
}

