plugins {
    id("internal.kmp.library")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.di"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":platform"))
            implementation(project(":settings"))
            implementation(project(":storage"))
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(project(":stt-android"))
            implementation(project(":pdf-android"))
            implementation(libs.vosk.android)
            implementation(libs.koin.android)
        }

        jvmMain.dependencies {
            implementation(project(":stt-desktop"))
            implementation(project(":pdf-desktop"))
            implementation(libs.vosk.core)
            implementation(libs.koin.core)
            //implementation(libs.snakeyaml)
            //implementation(libs.bundles.itext)
        }
    }
}
