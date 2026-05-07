package com.sildeag.buildlogic.plantuml

import com.sildeag.buildlogic.graph.DiGraph

class PumlTemplate {
    fun render(graph: DiGraph): String = buildString {
        appendLine("@startuml")
        appendLine("skinparam dpi 150")
        graph.nodes.groupBy { it.group }.forEach { (group, nodes) ->
            if (group != null) {
                appendLine("package \"$group\" {")
                nodes.forEach { node ->
                    appendLine(" component \"${node.label}\" as ${node.id}")
                }
                appendLine("}")
            } else {
                nodes.forEach { node ->
                    appendLine("component \"${node.label}\" as ${node.id}")
                }
            }
        }
        graph.edges.forEach { edge ->
            appendLine("${edge.from} --> ${edge.to}")
        }
        appendLine("@enduml")
    }
}
