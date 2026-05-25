package com.sildeag.sound2text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import org.koin.core.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.startKoin
/*
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(this@MyApplication)
            modules(
                coreModule, // shared logging
                configModule, // AppSettings loader
                sttModule, // Android STT engines (when
                added)
            androidStorageModule, // Android storage binding
            androidUiModule // Android UI/controller
            )
        }
    }
}
*/

// Thin MainActivity using appcommon
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsLoader.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = this
        val settings = SettingsLoader.load()
        startKoin {
            android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(this@MainActivity)
            modules(androidModule(settings))
        }
        setContent {
            com.sildeag.sound2text.appcommon.runApp()
        }
    }
}

/*
// Original ManiActivity using :ui-android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsLoader.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = this
        val settings: AppSettings = SettingsLoader.load()
        startKoin {
            android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(this@MainActivity)
            modules(androidModule(settings))
        }
        val logger: Logger by inject()
        val storage: StorageService by inject()
        val stt: SpeechToTextService by inject()
        logger.info("Sound2Text Android starting in ${settings.mode} mode")
                setContent {
            AndroidComposeApp(storage, stt, settings, logger)
        }
    }
}
*/

/****** Test stub keep for future reference
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAndroidApp()
        }
    }
}

@Composable
fun HelloAndroidApp() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "Android, says hello!")
    }
}

@Preview(showBackground = true)
@Composable
fun HelloAndroidPreview() {
    HelloAndroidApp()
}
*/
