package com.sildeag.sound2text.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sildeag.sound2text.android.ui.HomeScreen
import org.koin.android.ext.koin.android// TODO: inject platform // TODO: inject platform // TODO: inject platform context

startKoin {
    androidContext(this@MainActivity)
    modules(
        androidPlatformModule, // platform identity
        configModule, // environment + platform config
        coreModule, // shared logic
        coreUiModule, // shared UI logic
        voskModule, // STT engine
        androidSettingsModule, //
        androidAudioModule, //
        androidPermissionsModule //
    )
}