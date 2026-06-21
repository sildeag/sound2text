plugins {
    id("internal.kmp.library")
    //id("internal.kmp.room")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.storage"
    }

    sourceSets {
        commonMain.dependencies {
            // commonMain dependencies are in internal.kmp.base
            //implementation(libs.androidx.room.runtime)
            //implementation(libs.androidx.sqlite.bundled) // Bundled core SQLite
        }
        
        androidMain.dependencies {
            //implementation(libs.androidx.sqlite.framework)
            // Android gets the spatial binary payload
            //implementation(libs.spatialite.android)
        }
        jvmMain.dependencies {
            // Desktop native C driver via JDBC
            //implementation(libs.sqlite.jdbc)
            //implementation(libs.bundles.itext)
        }
    }
}
