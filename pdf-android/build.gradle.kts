plugins {
    id("internal.kmp.android.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            //implementation(project(":di"))
        }
        commonTest.dependencies {
            implementation(project(":core"))

            //implementation(project(":di"))

        }
        androidMain.dependencies {
            implementation(libs.vosk.android)
            implementation(libs.coroutines.android)
            // Correct way to use a BOM in a KMP source set:
            implementation(project.dependencies.platform(libs.compose.bom))
            implementation(libs.compose.ui)
        }
    }
}
