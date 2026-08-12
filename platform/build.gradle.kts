plugins {
    id("internal.kmp.platform")
    alias(libs.plugins.buildkonfig)
}


val jfxVersion = libs.versions.javafx.ver.get()

buildkonfig {
    packageName = "com.sildeag.sound2text.platform.config"
    objectName = "PlatformBuildConfig"

    defaultConfigs {
        // Use the fully qualified name for Type if it's not resolved
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN, "DEBUG", "true")
    }
}

kotlin {

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