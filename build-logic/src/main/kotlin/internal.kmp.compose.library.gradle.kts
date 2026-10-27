import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    android {
        namespace = "com.sildeag.sound2text." + project.name.replace("-", "")
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        // 1. REMOVED withHostTest {} completely to stop the early method exception.
    }

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

// 2. Safe Fallback: Satisfy any internal tasks looking for the configuration
// by explicitly neutralizing it at the root configuration collection level.
configurations.configureEach {
    if (name.contains("androidHostTestResolvableDependenciesMetadata")) {
        @Suppress("UnstableApiUsage")
        isCanBeResolved = false
    }
}

/*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    android {
        namespace = "com.sildeag.sound2text." + project.name.replace("-", "")
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        withHostTest {}
    }

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
// 1. Prevent the host test task from executing
tasks.matching { it.name.contains("compileAndroidHostTestKotlin") }.configureEach {
    enabled = false
}
// Safely bypass the internal AGP shouldResolveConsistentlyWith plugin bug
configurations.matching { it.name == "androidHostTestResolvableDependenciesMetadata" }.configureEach {
    // Prevent the plugin from forcing a consistent resolution lock that triggers the crash
    @Suppress("UnstableApiUsage")
    isCanBeResolved = false
}
// 2. Safely tell Gradle not to resolve or download artifacts for these configurations
configurations.matching {
    it.name.startsWith("androidHostTest") || it.name.startsWith("androidHostUnitTest")
}.configureEach {
    isCanBeResolved = false
}
*/






/*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    // id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = the<VersionCatalogsExtension>().named("libs")

// Configure the core Android block explicitly
android {
    namespace = "com.sildeag.sound2text.${project.name.replace("-", "")}"
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
}

configure<KotlinMultiplatformExtension> {
    // Declare the standard android target instead of the experimental one
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.findLibrary("compose-mpp-runtime").get())
            implementation(libs.findLibrary("compose-mpp-foundation").get())
            implementation(libs.findLibrary("compose-mpp-material3").get())
            implementation(libs.findLibrary("compose-mpp-material-icons-ext").get())
            implementation(libs.findLibrary("compose-mpp-viewmodel").get())
            implementation(libs.findLibrary("koin-compose").get())
        }
    }
}
*/
/*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    // id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    android {
        namespace = "com.sildeag.sound2text." + project.name.replace("-", "")
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        withHostTest {}
    }

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
*/
