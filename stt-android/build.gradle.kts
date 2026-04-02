plugins {
    id("internal.android.library")
}

android {
    namespace = "com.sildeag.sound2text.stt.android" // Standardized name

    defaultConfig {
        // REMOVE THIS LINE:
        // applicationId = "com.sildeag.sound2text.stt"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.vosk.android)
}

dependencies {
    implementation(libs.vosk.android)
}
