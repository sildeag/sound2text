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
        }

        jvmMain.dependencies {
            implementation(libs.vosk.model.en)
            implementation(libs.vosk.core)
        }

        androidMain.dependencies {
            implementation(libs.vosk.android)
        }
    }
}
