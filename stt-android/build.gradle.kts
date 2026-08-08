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
            implementation(libs.vosk.android)
            implementation(libs.jna.android)
            implementation(libs.vosk.model.en)
            implementation(libs.coroutines.android)
        }
    }
}
