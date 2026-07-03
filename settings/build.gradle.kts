plugins {
    id("internal.kmp.library")
    //id("internal.kmp.room")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.settings"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":storage"))
            // commonMain dependencies are in internal.kmp.base
            //implementation(libs.androidx.room.runtime)
            //implementation(libs.androidx.sqlite.bundled) // Bundled core SQLite
        }
        androidMain.dependencies {

        }
        jvmMain.dependencies {

        }
        

    }
}
