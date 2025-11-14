plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    androidTarget() // ✅ Required for Android builds

    jvm("desktop") {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
                }
            }
        }
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting
        val androidUnitTest by getting

        /*
        jvm("desktop") {/*
            compilations["main"].defaultSourceSet {
            java.srcDirs("src/desktopMain/java")
            kotlin.srcDirs("src/desktopMain/kotlin")
            resources.srcDirs("src/desktopMain/resources")
            */}
        }
        */
        val desktopMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.misc.vosk)
                implementation(libs.bundles.logging)
                implementation(libs.misc.snakeyaml)
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.bundles.itext)
                implementation(libs.misc.vosk)
                implementation(libs.bundles.logging)
                implementation(libs.misc.snakeyaml)
            }
        }
    }
}

android {
    namespace = "com.sildeag.sound2text"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

/*
android {
    namespace = "com.sildeag.sound2text"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.sildeag.sound2text"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
*/