package com.sildeag.sound2text.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sildeag.sound2text.android.ui.HomeScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(
                coreModule,
                coreUiModule,
                pulseLogicModule,
                coreSoundViewModelModule,
                voskEngineModule,
                androidSettingsModule,
                androidAudioModule,
                androidPermissionsModule
            )
        }
        setContent {
            HomeScreen()
        }
    }
}