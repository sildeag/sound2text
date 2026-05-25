package com.sildeag.buildlogic

import org.gradle.api.Project
import java.io.File

object DiagramFiles {

    /**
     * settings.gradle.kts at the root of the repo.
     */
    fun settingsFile(project: Project): File =
        project.rootProject
            .layout
            .projectDirectory
            .file("settings.gradle.kts")
            .asFile

    /**
     * di/src at the root of the repo.
     */
    fun diDir(project: Project): File =
        project.rootProject
            .layout
            .projectDirectory
            .dir("di/src")
            .asFile

    /**
     * build/di/<subDir> under the current project’s build directory.
     * Ensures the directory exists.
     */
    fun outputDir(project: Project, subDir: String): File =
        project.layout
            .buildDirectory
            .dir("di/$subDir")
            .get()
            .asFile
            .apply { mkdirs() }
}
