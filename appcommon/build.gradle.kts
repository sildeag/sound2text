plugins {
    id("internal.kmp.compose.library")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.appcommon"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":di"))
            // commonMain dependencies are in internal.kmp.base
        }
        commonTest.dependencies {

        }
        
        androidMain.dependencies {
            implementation(libs.androidx.compose.runtime)
            implementation(libs.vosk.android)
        }
        jvmMain.dependencies {
            // commonMain dependencies are in internal.kmp.base
        }
        jvmTest.dependencies {
            // commonMain dependencies are in internal.kmp.base
        }
    }
}
