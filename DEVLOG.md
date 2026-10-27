# Developer Log: sound2text

## Milestone: Project Modernization & Gradle 10 Readiness
**Date: 2025-05-23**

### Technical Direction & Acknowledgements
- **Architectural Guidance**: This project follows technical direction provided by Microsoft Copilot.
- **Modernization Strategy**: Focused on early adoption of **Kotlin 2.3.10** and **AGP 9.0.1** to leverage the latest compiler performance and multiplatform features.
- **Android Studio KMP/CMP**: This project has been assisted by Android Studio AI with Gradle KMP/CMP best practices.

### Key Updates
- **Toolchain Alignment**: Updated to **Kotlin 2.3.10** and **AGP 9.1.0** to resolve KMP-CMP compatibility issues and internal plugin bugs.
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
- Follow latest stable KMP architecture and Gradle syntax changes.

### Android Target Automation Reference

This document preserves the convention plugin automation for dynamic `namespace` generation, `compileSdk`, and `minSdk` configuration across Kotlin Multiplatform (KMP) modules.

---

## 1. AGP 8.x & Legacy KMP Configuration (`com.android.library` + `org.jetbrains.kotlin.multiplatform`)

In AGP 8.x / early AGP 9.0 compatibility mode, the `LibraryExtension` was configured at top-level in precompiled script plugins:

```kotlin
// build-logic/src/main/kotlin/internal.kmp.library.gradle.kts
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    androidTarget()
}

extensions.configure<com.android.build.gradle.LibraryExtension> {
    namespace = "${project.group}.${project.name.replace("-", "")}"
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
}
```

---

## 2. AGP 9.0+ New KMP DSL Configuration (`com.android.kotlin.multiplatform.library`)

In AGP 9.0+ (and AGP 9.5 Canary), AGP introduced the official `com.android.kotlin.multiplatform.library` plugin. The DSL replaces `androidTarget()` with `androidTarget()` / `androidLibrary()` and uses `KotlinMultiplatformAndroidExtension`:

```kotlin
// build-logic/src/main/kotlin/internal.kmp.library.gradle.kts
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    androidTarget()
}

extensions.configure<KotlinMultiplatformAndroidExtension> {
    namespace = "${project.group}.${project.name.replace("-", "")}"
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
    minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
}
```

---

## 3. Dynamic Namespace & SDK Logic Breakdown

- **Dynamic Namespace:** `${project.group}.${project.name.replace("-", "")}`  
  Automatically converts a module name like `:ui-common` under group `com.sildeag.wac2wav` into `com.sildeag.wac2wav.uicommon`.
- **Centralized SDK Versions:**  
  Pulls `android-compileSdk` and `android-minSdk` directly from `gradle/libs.versions.toml`:
  ```toml
  [versions]
  android-compileSdk = "35"
  android-minSdk = "24"
  ```

### AGP 9.6 & Kotlin Multiplatform Compose Configuration Summary

## 1. Overview & Architecture
This document summarizes the build and toolchain configuration for running **Kotlin Multiplatform (KMP)** with Compose Multiplatform on bleeding-edge **AGP 9.6.0-alpha01 ("Rabbit 3")**, **Kotlin 2.4.0**, and **Koin 4.2.0**, without requiring KSP or kapt.

---

## 2. ServiceLoader & AutoService Without KSP/kapt

### The Mechanism
* **Google `@AutoService` in Java vs Kotlin:**
  * In standard Java (`javac`), Google AutoService uses Java's built-in Pluggable Annotation Processing API (`javax.annotation.processing.Processor`) to generate `META-INF/services/` files.
  * In Kotlin (`kotlinc`), annotation processors are **not** invoked natively unless KSP or kapt is configured.
* **The Pure KMP Solution (Zero Build Overhead):**
  * Java/Android `ServiceLoader` reads plain-text files from `src/commonMain/resources/META-INF/services/com.sildeag.wac2wav.di.FeatureModuleProvider`.
  * Placing the provider class name (e.g., `com.sildeag.wac2wav.feature1.Feature1Provider`) in this file allows `ServiceLoader` to resolve feature modules dynamically across Android and Desktop/JVM with zero compiler plugins.

---

## 3. AGP 9.6.0-alpha01 & Compose Multiplatform Compatibility

### The `onVariant` Issue
* In AGP `9.6.0-alpha01`, Google refactored the internal `KotlinMultiplatformAndroidComponentsExtension` interface.
* Applying `id("org.jetbrains.compose")` on Android KMP library modules calls the legacy `onVariant()` method, resulting in `NoSuchMethodError` during Gradle sync.

### The Solution for KMP Library Convention Plugins
* In Kotlin 2.0+, Compose compilation is handled by Kotlin's official Compose compiler plugin (`id("org.jetbrains.kotlin.plugin.compose")`).
* `internal.kmp.compose.library` applies `id("org.jetbrains.kotlin.plugin.compose")` and imports Compose Multiplatform libraries (`libs.compose.mpp.runtime`, `libs.compose.mpp.material3`, etc.) directly from Version Catalog, bypassing the AGP 9.6 plugin hook crash.

---

## 4. Replacing `compose.desktop.currentOs` for Desktop JVM

### Explicit Platform Coordinate Replacement
When using pure KMP build scripts without the `id("org.jetbrains.compose")` plugin, `compose.desktop.currentOs` can be replaced with explicit Maven coordinates:

```kotlin
// In jvmMain.dependencies or build script:
val composeMppVer = libs.findVersion("compose-mpp").get().requiredVersion

jvmMain.dependencies {
    implementation("org.jetbrains.compose.desktop:desktop:$composeMppVer")
    // 2. Add this line to explicitly force Gradle to package
    // the missing Windows native binary components into the jvmRun runtime environment
    // Explicit platform coordinate replacement for compose.desktop.currentOs
    runtimeOnly("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:$composeMppVer")
}
```

### Dynamic Multi-OS Helper Function (Cross-Platform)
For cross-platform support (Windows, macOS x64/arm64, Linux) across team environments:

```kotlin
fun currentOsDesktopTarget(): String {
    val os = System.getProperty("os.name").lowercase()
    val arch = System.getProperty("os.arch").lowercase()
    val platform = when {
        os.contains("win") -> "windows-x64"
        os.contains("mac") -> if (arch == "aarch64" || arch == "arm64") "macos-arm64" else "macos-x64"
        else -> "linux-x64"
    }
    return "org.jetbrains.compose.desktop:desktop-jvm-$platform"
}

jvmMain.dependencies {
    implementation("org.jetbrains.compose.desktop:desktop:$composeMppVer")
    runtimeOnly("${currentOsDesktopTarget()}:$composeMppVer")
}
```


### AGP 9.x & KMP Migration and Convention Plugins Reference

This document summarizes AGP 9.x migration principles, convention plugin structures, and root classpath management for Kotlin Multiplatform (KMP).

---

## 1. The Migration Principles (AGP 9.5 & Beyond)

In AGP 9.0+, Google deprecated and removed applying legacy `com.android.library` alongside `org.jetbrains.kotlin.multiplatform`.

### Permanent KMP Architecture
For all KMP library modules (`:ui-common`, `:feature1`, `:di`), the official permanent plugin is **`com.android.kotlin.multiplatform.library`**:

```kotlin
// build-logic/src/main/kotlin/internal.kmp.compose.library.gradle.kts
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library") // <--- Permanent AGP 9+ KMP plugin
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    androidTarget()

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                implementation(libs.findLibrary("compose-mpp-runtime").get())
                implementation(libs.findLibrary("compose-mpp-foundation").get())
                implementation(libs.findLibrary("compose-mpp-material3").get())
                implementation(libs.findLibrary("compose-mpp-material-icons-ext").get())
                implementation(libs.findLibrary("compose-mpp-viewmodel").get())
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
    }
}
```

> **Future Stability Note:**  
> When updating AGP from `9.5.0-alpha10` to `9.6` or `10.0`, this convention plugin DSL stays identical. Only the `agp` version string in `gradle/libs.versions.toml` changes.

---

## 2. Dynamic `namespace` & Package Rules

Android namespaces cannot contain hyphens (`-`). Use string manipulation for dynamic namespaces in convention plugins:

```kotlin
namespace = "${project.group}.${project.name.replace("-", "")}"
```

- Module `:ui-common` under group `com.sildeag.wac2wav` automatically becomes valid Java package `com.sildeag.wac2wav.uicommon`.

---

## 3. Fixing the `com.android.library` / `com.android.application` Exception in AGP 9.0+

If you see:  
`java.lang.IllegalStateException: The 'com.android.library' (or 'com.android.application') plugin is not compatible with 'org.jetbrains.kotlin.multiplatform' since AGP 9.0`

### Resolution Matrix by Module Type:

| Module Type | Convention Plugin | Plugins Applied Under the Hood |
| :--- | :--- | :--- |
| **KMP Feature / UI Modules** (`:ui-common`, `:feature1`) | `id("internal.kmp.compose.library")` | `com.android.kotlin.multiplatform.library` + `org.jetbrains.kotlin.multiplatform` |
| **Pure Android Library** (Android-only) | `id("internal.android.library")` | `com.android.library` + `org.jetbrains.kotlin.android` |
| **Pure Android App Launcher** (`:app-android`) | `id("internal.android.application")` | `com.android.application` + `org.jetbrains.kotlin.android` |

---

## 4. Root `build.gradle.kts` & `apply false` Rule

In root `build.gradle.kts`, declaring plugins with **`apply false`** loads plugin JARs into Gradle's buildscript classpath without applying plugin execution logic to any project.

### Root `build.gradle.kts` Template:

```kotlin
// build.gradle.kts (Root Project)
plugins {
    // Android AGP 9.x
    alias(libs.plugins.androidKmp) apply false          // com.android.kotlin.multiplatform.library
    alias(libs.plugins.androidApplication) apply false  // com.android.application
    alias(libs.plugins.androidLibrary) apply false      // com.android.library

    // Kotlin
    alias(libs.plugins.kotlinMultiplatform) apply false // org.jetbrains.kotlin.multiplatform
    alias(libs.plugins.kotlinx.serialization) apply false

    // Compose Multiplatform
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false

    // Tooling
    alias(libs.plugins.dokka) apply false
    jacoco
    id("internal.verification")
}
```

- **Rule:** As long as `apply false` is used at root, conflicting plugin declarations on the root classpath **never cause clashes**. Clashes only occur if a single `:module` applies incompatible plugins simultaneously.

# Kotlin, KSP, and Gradle Configuration Guide

This document provides recommended version combinations and `gradle.properties` configurations for Kotlin, KSP, Compose Multiplatform, and Android Gradle Plugin (AGP) when targeting bleeding-edge setup or stable stacks.

---

### 1. KSP & Kotlin Version Mapping Table
# KSP was abandoned because its use severely down-graded the versions available for
# use in sound2text.

KSP release tags strictly enforce a 1:1 mapping with the Kotlin compiler version using the format:
`<Kotlin-Version>-<KSP-Release-Version>`

| Kotlin Version | Compatible KSP Release Tag | Notes / Status |
| :--- | :--- | :--- |
| **2.1.20** | `2.1.20-1.0.31` | Latest 2.1.x patch |
| **2.1.10** | `2.1.10-1.0.30`, `2.1.10-1.0.29` | Stable |
| **2.1.0** | `2.1.0-1.0.29` | Recommended for Kotlin 2.1 |
| **2.0.21** | `2.0.21-1.0.28`, `2.0.21-1.0.25` | Stable |
| **2.0.20** | `2.0.20-1.0.25`, `2.0.20-1.0.24` | Stable K2 Compiler baseline |
| **2.0.10** | `2.0.10-1.0.24` | Baseline |
| **2.0.0** | `2.0.0-1.0.24`, `2.0.0-1.0.22`, `2.0.0-1.0.21` | Initial K2 Compiler release |

> **Note on Future / Canary Versions:**  
> When Kotlin is updated (e.g. 2.1.x or 2.2.x), KSP must match the exact Kotlin version prefix. If KSP is disabled or replaced with `@AutoService` (ServiceLoader annotation processor), you do not need the KSP plugin.

---

## 2. Compose Multiplatform & Kotlin Alignment Stack

| Compose Multiplatform | Compatible Kotlin Version | Compose Compiler Version | Recommended KSP Version |
| :--- | :--- | :--- | :--- |
| **1.7.3** | `2.0.21` or `2.1.0` | Built-in (`id("org.jetbrains.kotlin.plugin.compose")`) | `2.0.21-1.0.28` or `2.1.0-1.0.29` |
| **1.7.0** | `2.0.20` | Built-in | `2.0.20-1.0.25` |
| **1.8.0-dev / Bleeding Edge** | `2.1.0` / `2.1.10` | Built-in | `2.1.10-1.0.30` |

---

## 3. Recommended `gradle.properties` Settings

For modern AGP 8.7+ / AGP 9.0 Canary (e.g., Android Studio Ladybug / Meerkat / Baklava Canary):

```properties
# 1. Performance & Memory
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m -XX:+UseParallelGC -Dfile.encoding=UTF-8
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.daemon=true
org.gradle.configuration-cache=true

# 2. AndroidX & Modern DSL
android.useAndroidX=true
android.nonTransitiveRClass=true

# 3. AGP 9.0 Canary KMP Compatibility Flags
# When using 'com.android.library' with 'org.jetbrains.kotlin.multiplatform':
android.builtInKotlin=false
android.newDsl=false
android.enableLegacyVariantApi=true
android.disallowKotlinSourceSets=false

# 4. Toolchain & JVM Target
# Ensure JDK 21 is set in build-logic toolchains to match your local installation:
# toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
```

---

## 4. AGP 9.0 Canary Bleeding Edge Migration

If you are upgrading to AGP 9.0 Canary (e.g., Studio Canary / Rabbit 2):

1. **Replace `com.android.library` in KMP modules:**
   In AGP 9.0, `com.android.library` is no longer supported directly alongside `org.jetbrains.kotlin.multiplatform`. Replace `com.android.library` with `com.android.kotlin.multiplatform.library` in your convention plugins (`build-logic`).
   ```kotlin
   // In convention plugins:
   plugins {
       id("com.android.kotlin.multiplatform.library")
   }
   ```
2. **Replacing KSP with `@AutoService`:**
   If you replace KSP with `@AutoService` (from Google AutoService or Java ServiceLoader plugin), apply the Java compiler annotation processor directly in `jvmMain` / `commonMain` or `java` block without applying the `com.google.devtools.ksp` plugin.

---

## 5. Key Fixes Applied to the Build System

1. **Skiko Native Library Conflict Fixed (`UnsatisfiedLinkError`):**  
   Removed hardcoded `runtimeOnly("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.8.0")` from `appcommon/build.gradle.kts`. Skiko is managed automatically by `implementation(compose.desktop.currentOs)`.

2. **Java Toolchain Updated to JDK 21:**  
   Updated `build-logic/build.gradle.kts` toolchain from Java 17 to Java 21 to match the installed JDK at `C:\Program Files\Java\jdk-21`.

3. **Resource Duplication Fix:**  
   Added `duplicatesStrategy = DuplicatesStrategy.EXCLUDE` for `Copy` tasks in `internal.kmp.base.gradle.kts` to prevent duplicate `META-INF/services` file conflicts during processResources tasks.

