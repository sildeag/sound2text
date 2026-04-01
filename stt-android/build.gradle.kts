plugins {
    id("internal.android.library")
}

android {
    namespace = "com.sildeag.sound2text.sttdesktop.android"
    
    defaultConfig {
        applicationId = "com.sildeag.sound2text.stt"
    }
}

dependencies {
    implementation(libs.vosk.android)
}
