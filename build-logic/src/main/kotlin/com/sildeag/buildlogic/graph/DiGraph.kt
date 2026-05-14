package com.sildeag.buildlogic.graph

data class DiNode(
    val id: String,
    val label: String = id,
    val group: String? = null
)
data class DiEdge(
    val from: String,
    val to: String
)
data class DiGraph(
    val name: String,
    val nodes: MutableSet<DiNode> = mutableSetOf(),
    val edges: MutableSet<DiEdge> = mutableSetOf()
) {
    fun addNode(id: String, label: String = id, group: String? =
        null) {
        nodes.add(DiNode(id, label, group))
    }
    fun addEdge(from: String, to: String) {
        edges.add(DiEdge(from, to))
    }
}
