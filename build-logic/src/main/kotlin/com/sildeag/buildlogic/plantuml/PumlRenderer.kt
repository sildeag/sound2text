package com.sildeag.buildlogic.plantuml

import org.gradle.process.ExecOperations
import java.io.File
import javax.inject.Inject
class PlantUmlRenderer @Inject constructor(
    private val execOps: ExecOperations
) {
    fun render(file: File, jar: File) {
        execOps.exec {
            commandLine("java", "-jar", jar.absolutePath, "-tpng",
                file.absolutePath)
        }
        execOps.exec {
            commandLine("java", "-jar", jar.absolutePath, "-tsvg",
                file.absolutePath)
        }
    }
}
