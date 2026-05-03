/**
 * Draft / Experimental Tasks
 * Use this file to try out new script ideas.
 * Once finalized, move them to build-logic/src/main/kotlin/internal.verification.gradle.kts
 */

tasks.register("checkUnusedDiDraft") {
    group = "verification-draft"
    description = "Checks for unused DI bindings (Draft Version - Edit scripts/draft-tasks.gradle.kts to change)"

    doLast {
        println("--- [DRAFT] Running experimental DI check ---")
        // You can experiment with new logic here without breaking the stable version
        // Example: Add more complex regex or check for imports
        println("Draft check complete.")
    }
}
