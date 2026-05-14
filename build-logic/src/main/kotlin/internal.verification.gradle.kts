/**
 * Stable Verification Tasks
 * Move tasks here once they are thoroughly tested.
 */
tasks.register("checkUnusedDi") {
    group = "verification"
    description = "Checks for unused DI bindings across the project (Stable Version)"

    doLast {
        val root = project.rootDir
        val diSrc = File(root, "di/src")
        
        if (!diSrc.exists()) {
            println("DI source directory not found at ${diSrc.absolutePath}")
            return@doLast
        }

        val diFiles = diSrc.walkTopDown().filter { it.isFile && it.extension == "kt" }.toList()
        val allFiles = root.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { !it.absolutePath.contains("${File.separator}build${File.separator}") }
            .toList()

        val bindingRegex = Regex("""(single|factory)\s*<\s*([A-Za-z0-9_.]+)\s*>\s*\{""")
        val bindings = mutableSetOf<String>()

        diFiles.forEach { file ->
            bindingRegex.findAll(file.readText()).forEach { match ->
                bindings.add(match.groupValues[2])
            }
        }

        println("--- [STABLE] DI Usage Check ---")
        var unusedCount = 0
        bindings.forEach { type ->
            val usedOutsideDi = allFiles.any { file ->
                !file.absolutePath.startsWith(diSrc.absolutePath) && file.readText().contains(type)
            }
            if (!usedOutsideDi) {
                println("[!] Possibly unused: $type")
                unusedCount++
            }
        }
        println(if (unusedCount == 0) "All bindings appear used." else "Found $unusedCount possibly unused.")
        println("-------------------------------")
    }
}
