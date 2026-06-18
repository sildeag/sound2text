plugins {
    id("internal.kmp.android.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            //implementation(project(":di"))

        }
        androidMain.dependencies {
                implementation(libs.vosk.android)
        }
    }
}
