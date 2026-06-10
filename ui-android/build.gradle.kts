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
    //implementation(project(":core"))
    implementation(project(":ui-common"))
    implementation(project(":feature-recording"))
    //implementation(project(":pdf-android"))
    //implementation(project(":stt-android"))
    implementation(libs.kotlinx.serialization.json)
    
    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)
}
