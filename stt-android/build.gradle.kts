plugins {
    id("internal.kmp.android.library")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))
            }
        }
        androidMain {
            dependencies {
                implementation(libs.vosk.android)
            }
        }
    }
}
