plugins {
    id("internal.kmp.compose.library")
    //id("internal.kmp.room")
}

val jfxVersion: String = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.core"
    }

    sourceSets {
        commonMain.dependencies {
            // commonMain dependencies are in internal.kmp.base
            //implementation(libs.androidx.room.runtime)
            implementation(libs.compose.mpp.ui)

        }
        
        androidMain.dependencies {
            implementation(libs.bundles.itext)
            implementation(libs.pdfbox.android)
            // Android gets the spatial binary payload
            //implementation(libs.spatialite.android)
        }
        jvmMain.dependencies {
            // Desktop native C driver via JDBC
            implementation(libs.bundles.itext)
            implementation(libs.pdfbox)
        }
    }
}
