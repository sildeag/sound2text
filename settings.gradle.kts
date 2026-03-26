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
    ":core",
    ":di",
    ":pdf",
    ":stt",
    ":stt-android",
    ":ui-android",
    ":ui-common",
    ":ui-desktop",
    ":ui-legacy"
)
