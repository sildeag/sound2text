plugins {
    //id("internal.kmp.compose.library")
    id("internal.kmp.test")
}

kotlin {
    android {
        namespace = "com.sildeag.sound2text.test.harness"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(libs.vosk)
            implementation(libs.vosk.api)
        }

        androidMain.dependencies {
            implementation(libs.vosk.android)
        }
    }
}
