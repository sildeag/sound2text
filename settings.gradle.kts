enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

        // JetBrains Compose dev plugin repository
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            // Correct syntax block specifically for plugin management
            mavenContent {
                includeGroup("org.jetbrains.compose")
            }
        }
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()

        // JetBrains Compose dev artifacts repo
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            content {
                includeGroupAndSubgroups("org.jetbrains.compose")
            }
        }

        // JitPack legacy repository
        maven("https://jitpack.io") {
            content {
                includeGroup("com.github")
            }
        }

        // Dedicated legacy repositories
        maven("itextsupport.com")
        maven("clojars.org")
    }
}

buildCache {
    local {
        isEnabled = true
    }
}

rootProject.name = "sound2text"

include(
    ":appcommon",
    ":core",
    ":di",
    ":feature-history",
    ":feature-recording",
    ":feature-settings",
    ":pdf-android",
    ":pdf-desktop",
    ":settings",
    ":storage",
    ":stt-desktop",
    ":stt-android",
    ":ui-android",
    ":ui-common",
    ":ui-desktop",
    ":test-harness",
    ":ui-legacy"
)

/*
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        // ✅ JetBrains Compose plugin repo
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven { url = java.net.URI("https://jitpack.io") } // <-- Add this line
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        // ✅ JetBrains Compose artifacts repo
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://repo.itextsupport.com/releases")
        maven("https://repo.clojars.org/")
        maven("https://mvnrepository.com/artifact/app.softwork/kotlinx-uuid-core")
    }
}

buildCache {
    local {
        isEnabled = true
    }
}

rootProject.name = "sound2text"

include(
    ":appcommon",
    ":core",
    ":di",
    ":pdf-android",
    ":pdf-desktop",
    ":stt-desktop",
    ":stt-android",
    ":ui-android",
    ":ui-common",
    ":ui-desktop",
    ":ui-legacy"
)
*/