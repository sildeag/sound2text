package com.sildeag.buildlogic

import org.gradle.api.Project
import java.io.File
object DiagramFiles {
    fun settingsFile(project: Project): File =
        File(project.rootDir, "settings.gradle.kts")
    fun diDir(project: Project): File =
        File(project.rootDir, "di/src")
    fun outputDir(project: Project, subDir: String): File =
        File(project.rootDir, "build/di/$subDir").apply { mkdirs() }
}