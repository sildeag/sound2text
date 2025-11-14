import org.gradle.kotlin.dsl.desktop

//val libs = extensions.getByName("libs") as VersionCatalog
/*
plugins {
    id("com.android.application") version "8.4.1"
    kotlin("multiplatform") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
    id("com.google.devtools.ksp") version "2.3.0"
    //alias(libs.plugins.ksp)
}

 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.kotlinMultiplatform)
    //kotlin("multiplatform") version "2.0.21"
    //id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    //id("com.google.devtools.ksp") version "2.3.0"
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sildeag.sound2text"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.sildeag.sound2text"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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

    /*
    kotlinOptions {
        jvmTarget = "17"
    }

     */

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.android)
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.bundles.koin.android)
    implementation(libs.misc.vosk.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.koin.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
