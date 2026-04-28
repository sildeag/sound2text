plugins {
    id("internal.android.application")
}

android {
    namespace = "com.sildeag.sound2text"
    
    defaultConfig {
        applicationId = "com.sildeag.sound2text"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui-common"))
    implementation(project(":stt-android"))
    implementation(project(":di"))
    implementation(libs.koin.android)
    //implementation(libs.koin.compose)
    //implementation(libs.compose.material3)
}
