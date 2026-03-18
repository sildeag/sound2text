plugins {
    id("internal.android.application")
}

android {
    namespace = "com.sildeag.sound2text.ui.android"
    
    defaultConfig {
        applicationId = "com.sildeag.sound2text"
    }
}

dependencies {
    implementation(project(":ui-common"))
    implementation(project(":stt-android"))
    implementation(libs.vosk.android)
}
