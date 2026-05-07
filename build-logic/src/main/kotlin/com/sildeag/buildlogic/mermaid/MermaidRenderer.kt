package com.sildeag.buildlogic.mermaid

import org.gradle.process.ExecOperations
import java.io.File
import javax.inject.Inject
class MermaidRenderer @Inject constructor(
    private val execOps: ExecOperations
) {
    fun render(file: File) {
        val base = file.absolutePath.removeSuffix(".mmd")
        execOps.exec {
            commandLine("mmdc", "-i", file.absolutePath, "-o", "${base}.png")
        }
        execOps.exec {
            commandLine("mmdc", "-i", file.absolutePath, "-o", "${base}.svg")
        }
    }
}
