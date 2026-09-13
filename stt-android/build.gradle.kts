plugins {
    id("internal.kmp.android.library")
}

kotlin {
    android {
        namespace = "com.sildeag.sound2text.sttandroid"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
        }
        androidMain.dependencies {
            implementation(libs.androidx.datastore.core)
            implementation(libs.vosk.android)
            // implementation(libs.jna.android) // Remove to resolve duplicate class error with vosk-android
            implementation(libs.vosk.model.en)
            implementation(libs.coroutines.android)
        }
    }
}
