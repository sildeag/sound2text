plugins {
    id("internal.android.application")
}

android {
    namespace = "com.sildeag.sound2text.stt.android"
    
    defaultConfig {
        applicationId = "com.sildeag.sound2text.stt"
    }
}

dependencies {
    implementation(project(":stt"))
    implementation(libs.vosk.android)
}
