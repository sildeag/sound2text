package com.sildeag.sound2text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sildeag.sound2text.core.storage.StorageService
import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.platform.SettingsLoader
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.SpeechToTextService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin
/*
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsLoader.context = this
        val settings: AppSettings = SettingsLoader.load()
        startKoin {
            androidContext(this@MainActivity)
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