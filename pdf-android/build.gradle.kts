plugins {
    id("internal.kmp.android.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core"))
            //implementation(project(":core"))
            //implementation(project(":ui-common"))
            //implementation(project(":di"))
        }
        commonTest.dependencies {
            implementation(project(":core"))

            //implementation(project(":di"))

        }
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.graphics)
            implementation(libs.coroutines.android)
            //implementation(libs.vosk.android)
            //implementation(libs.coroutines.android)
            // Correct way to use a BOM in a KMP source set:
            //implementation(project.dependencies.platform(libs.compose.bom))
            //implementation(libs.compose.ui)
        }
    }
}
// Use the explicit compilation target configuration for heavy, native Android AAR dependencies
dependencies {
    "androidCompilationImplementation"(libs.pdfbox.android)
    "androidCompilationImplementation"(libs.bundles.itext)
    //"androidCompilationImplementation"(libs.vosk.android)
    // Add Koin Android Bundle here for native compilation scope
    "androidCompilationImplementation"(libs.bundles.koin.android)

    // Or if referencing the individual core Koin Android library:
    // "androidCompilationImplementation"(libs.koin.android)
}

