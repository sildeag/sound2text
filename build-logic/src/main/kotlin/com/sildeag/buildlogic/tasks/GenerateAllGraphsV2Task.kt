package com.sildeag.buildlogic.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
abstract class GenerateAllGraphsV2Task : DefaultTask() {
    init {
        group = "verification"
        description = "Generates all diagrams using the modular engine"
    }
    @TaskAction
    fun runAll() {

        project.tasks.named("generateMermaidGraphsV2").get().actions.forEach {
            it.execute(this)
        }

        project.tasks.named("generatePumlGraphsV2").get().actions.forEach {
            it.execute(this)
        }
    }
}
