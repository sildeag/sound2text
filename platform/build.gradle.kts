plugins {
    id("internal.kmp.platform")
}

val jfxVersion = libs.versions.javafx.ver.get()

kotlin {
    android {
        namespace = "com.sildeag.sound2text.platform"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
        }
        
        jvmMain.dependencies {
        }

        androidMain.dependencies {
        }

        jvmTest.dependencies {
            implementation(libs.bundles.testJvm)
        }
    }
}
/*
compose.desktop {
    application {
        mainClass = "com.sildeag.sound2text.uicommon.MainKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "Sound2Text"
            packageVersion = "1.0.0"
        }
    }
}
*/