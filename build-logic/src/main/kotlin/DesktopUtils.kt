import org.gradle.api.Project

/*
 * EDUCATIONAL NOTE: Extension Property vs Extension Function
 *
 * 1. Extension Function (Requires parentheses):
 *    fun Project.getDesktopPlatform(): String { ... }
 *    Usage: val p = project.getDesktopPlatform()
 *
 * 2. Extension Property (Looks like a variable):
 *    val Project.desktopPlatform: String get() = ...
 *    Usage: val p = project.desktopPlatform
 *
 * We use the Property version in Gradle because it makes the DSL cleaner.
 */

/**
 * Extension property to provide a standardized platform string
 * (win, linux, mac, mac-aarch64) across all modules.
 *
 * Usage in build.gradle.kts:
 * implementation("org.openjfx:javafx-base:$jfxVersion:$desktopPlatform")
 */
val Project.desktopPlatform: String
    get() = run {
        val os = System.getProperty("os.name").lowercase()
        val arch = System.getProperty("os.arch")

        when {
            os.contains("win")   -> "win"
            os.contains("linux") -> "linux"
            os.contains("mac")   -> if (arch == "aarch64") "mac-aarch64" else "mac"
            else -> "win"
        }
    }
