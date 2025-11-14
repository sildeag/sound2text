enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/") // ✅ Ensures plugin resolution
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://repo.itextsupport.com/releases")
        maven("https://repo.clojars.org/")
        maven("https://mvnrepository.com/artifact/app.softwork/kotlinx-uuid-core")
    }
/*
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml")) // ✅ Only one call to 'from'
        }
    }

 */
}

buildCache {
    local {
        isEnabled = true
    }
}

rootProject.name = "sound2text"
include(":android-ui")
include(":core")
include(":desktop-ui")


buildCache {
    local {
        isEnabled = true
    }
}

rootProject.name = "sound2text"
include(":android-ui")
include(":core")
include(":desktop-ui")
