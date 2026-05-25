package com.sildeag.sound2text.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sildeag.sound2text.android.ui.HomeScreen
import org.koin.android.ext.koin.android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import org.koin.core.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(this@MainActivity)
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
