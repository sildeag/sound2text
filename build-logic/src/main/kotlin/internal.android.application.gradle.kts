plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    // id("org.jetbrains.dokka") // Dokka removed due to AGP 9.x compatibility issues
    id("jacoco")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("android-targetSdk").get().requiredVersion.toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Reverting to standard AGP 8.7.3 syntax (is- prefix)
            isMinifyEnabled = false
            
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().requiredVersion
    }
}

dependencies {
    implementation(platform(libs.findLibrary("compose-bom").get()))
    implementation(libs.findLibrary("compose-ui").get())
    implementation(libs.findLibrary("compose-material3").get())
    implementation(libs.findLibrary("koin-android").get())
    implementation(libs.findLibrary("activity-compose").get())
    implementation(libs.findLibrary("core-ktx").get())
}

// Solution for: The archives configuration has been deprecated for artifact declaration
afterEvaluate {
    configurations.findByName("archives")?.let { archivesConfig ->
        val legacyArtifacts = archivesConfig.artifacts
        if (legacyArtifacts.isNotEmpty()) {
            tasks.named("assemble") {
                dependsOn(legacyArtifacts.map { it.buildDependencies })
            }
            archivesConfig.artifacts.clear()
        }
    }
}
