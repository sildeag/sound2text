package com.sildeag.buildlogic.mermaid

import com.sildeag.buildlogic.graph.DiGraph
class MermaidTemplate {
    fun render(graph: DiGraph): String = buildString {
        appendLine("graph TD")
        // group nodes by group
        graph.nodes.groupBy { it.group }.forEach { (group, nodes) ->
            if (group != null) {
                appendLine("subgraph $group")
                nodes.forEach { node ->
                    appendLine(" ${node.id}[\"${node.label}\"]")
                }
                appendLine("end")
            } else {
                nodes.forEach { node ->
                    appendLine(" ${node.id}[\"${node.label}\"]")
                }
            }
        }
        graph.edges.forEach { edge ->
            appendLine(" ${edge.from} --> ${edge.to}")
        }
    }
}
