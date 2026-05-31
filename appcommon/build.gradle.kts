plugins {
    id("internal.kmp.library")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.core"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":di"))
            // commonMain dependencies are in internal.kmp.base
        }
        
        androidMain.dependencies {
            implementation(libs.vosk.android)
        }
        jvmMain.dependencies {
            // commonMain dependencies are in internal.kmp.base
        }
    }
}
