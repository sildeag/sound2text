plugins {
    id("internal.kmp.library")
    //id("internal.jvm") must not use this as it conflicts with KMP sourceSets
}

kotlin {
    android {
        namespace = "com.sildeag.sound2text.pdfdesktop"
    }

    sourceSets {
        commonMain.dependencies {
                implementation(project(":core"))
                implementation(project(":ui-common"))
                //implementation(project(":di"))
                implementation(libs.bundles.itext)
        }

        jvmMain.dependencies {
            implementation(libs.pdfbox)
            implementation(libs.djl.api)
            runtimeOnly(libs.djl.onnx)
        }

        androidMain.dependencies {
        }
    }
}
