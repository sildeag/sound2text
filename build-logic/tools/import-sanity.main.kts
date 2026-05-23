#!/usr/bin/env kotlin

import java.io.File

// ---------------------------------------------
// CONFIGURATION
// ---------------------------------------------
val rootDir = File(".")
val allowedRoot = "com.sildeag.sound2text"

// Source sets to scan
val sourceSets = listOf(
    "src/commonMain/kotlin",
    "src/androidMain/kotlin",
    "src/jvmMain/kotlin"
)

// ---------------------------------------------
// DISCOVER MODULES FROM settings.gradle.kts
// ---------------------------------------------
fun discoverModules(): List<String> {
    val settings = File("settings.gradle.kts")
    if (!settings.exists()) {
        println("[ERROR] settings.gradle.kts not found")
        return emptyList()
    }
    /* single line parser e.g. include(":ui-common", ":ui-android", ":ui-desktop")
    val includeRegex = Regex("""include\((.*?)\)""")
    val moduleRegex = Regex("""":(.*?)"""")
    */
    // 1. Extract the entire include(...) block, even if multiline or spaced
    val includeRegex = Regex(
        """include\s*\(\s*((?s).*?)\s*\)""",
        RegexOption.MULTILINE
    )

    // 2. Extract module names like ":ui-common", ":core", etc.
    val moduleRegex = Regex("""":([^"]+)"""")

    val content = settings.readText()
    //println("content: $content")
    val includes = includeRegex.findAll(content).flatMap { match ->
        moduleRegex.findAll(match.groupValues[1]).map { it.groupValues[1] }
    }.toList()

    println("Discovered modules: $includes")
    return includes
}

// ---------------------------------------------
// CHECK PACKAGE ALIGNMENT
// ---------------------------------------------
fun checkPackageAlignment(file: File, module: String): List<String> {
    val errors = mutableListOf<String>()
    val lines = file.readLines()

    val packageLine = lines.firstOrNull { it.trim().startsWith("package ") }
    if (packageLine == null) {
        errors += "Missing package declaration"
        return errors
    }

    val declaredPackage = packageLine.removePrefix("package").trim()
    val expectedPath = declaredPackage.replace(".", "/")

    // Locate src/<sourceSet>/kotlin/
    val srcIndex = file.path.indexOf("src${File.separator}")
    if (srcIndex == -1) {
        errors += "Cannot determine source set for file"
        return errors
    }

    val afterSrc = file.path.substring(srcIndex)
    val parts = afterSrc.split(File.separator)

    // Expect: src / <sourceSet> / kotlin / <package path> / File.kt
    if (parts.size < 4) {
        errors += "Unexpected folder structure: $afterSrc"
        return errors
    }

    val folderPath = parts.drop(3).dropLast(1).joinToString("/")

    if (folderPath != expectedPath) {
        errors += "Package mismatch: declared '$declaredPackage' but folder is '$folderPath'"
    }

    return errors
}


// ---------------------------------------------
// CHECK IMPORTS
// ---------------------------------------------
fun checkImports(file: File): List<String> {
    val errors = mutableListOf<String>()
    val lines = file.readLines()

    lines.filter { it.trim().startsWith("import ") }.forEach { line ->
        val importPath = line.removePrefix("import").trim()

        // Phantom imports
        if (importPath.startsWith("sound2text.")) {
            errors += "Phantom import: $importPath (missing 'com.sildeag')"
        }

        // Enforce fully-qualified imports
        if (importPath.contains("sound2text") && !importPath.startsWith(allowedRoot)) {
            errors += "Non-fully-qualified import: $importPath"
        }
    }

    return errors
}

// ---------------------------------------------
// SCAN MODULES
// ---------------------------------------------
fun scanModule(module: String) {
    println("\n=== Checking module: $module ===")

    val moduleDir = File(module)
    if (!moduleDir.exists()) {
        println("Module directory not found: $module")
        return
    }

    sourceSets.forEach { src ->
        val srcDir = File(moduleDir, src)
        if (!srcDir.exists()) return@forEach

        srcDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
            val pkgErrors = checkPackageAlignment(file, module)
            val importErrors = checkImports(file)

            if (pkgErrors.isNotEmpty() || importErrors.isNotEmpty()) {
                println("\nFile: ${file.path}")
                pkgErrors.forEach { println("  [PACKAGE] $it") }
                importErrors.forEach { println("  [IMPORT]  $it") }
            }
        }
    }
}

// ---------------------------------------------
// MAIN EXECUTION
// ---------------------------------------------
val modules = discoverModules()
modules.forEach { scanModule(it) }

println("\nImport sanity check complete.")
