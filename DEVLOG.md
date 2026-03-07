# Developer Log: sound2text

## Milestone: Project Modernization & Gradle 10 Readiness
**Date: 2025-05-23**

### Technical Direction & Acknowledgements
- **Architectural Guidance**: This project follows technical direction provided by Microsoft Copilot, with some assists from Android Studio AI focusing on KMP/CMP best practices.
- **Modernization Strategy**: Focused on early adoption of **Kotlin 2.3.10** and **AGP 9.0.1** to leverage the latest compiler performance and multiplatform features.

### Key Updates
- **Toolchain Alignment**: Updated to **Kotlin 2.3.10** and **AGP 9.0.1** to resolve KMP-CMP compatibility issues and internal plugin bugs.
- **Gradle 10 Compatibility**: 
    - Migrated Jacoco tasks to the lazy `tasks.register` API.
    - Replaced legacy `buildDir` with `layout.buildDirectory`.
    - Applied the `archives` configuration suppression to bypass legacy artifact warnings in AGP/Dokka.
- **Architecture Refactor**: 
    - Unified module namespaces to follow `com.sildeag.sound2text.[layer].[platform]` pattern.
    - Synchronized `expect`/`actual` compiler flags across modules.
- **Cross-Platform Build**: Implemented dynamic JavaFX platform detection (Win/Mac/Linux) in all JVM modules.

### Lessons Learned
- Always align `compose-compiler` version with the `kotlin-ver`.
- Prefer `androidKmp` (`com.android.kotlin.multiplatform.library`) for KMP modules over legacy `com.android.library` to avoid classpath version conflicts.
- Suppression of `archives` configuration is a necessary bridge for pre-release plugin versions in the AGP 9.0 era.
