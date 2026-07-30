package com.sildeag.sound2text.uiandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.startKoin
import com.sildeag.sound2text.uicommon.ui.AppRoot
import com.sildeag.sound2text.uicommon.navigation.AppNavigator
import com.sildeag.sound2text.uicommon.pdf.PdfViewModel
import com.sildeag.sound2text.featurerecording.SttViewModel
import com.sildeag.sound2text.uiandroid.ui.screens.AndroidPdfScreen
import com.sildeag.sound2text.uiandroid.di.pdfAndroidModule
import com.sildeag.sound2text.uiandroid.di.sttAndroidModule
import com.sildeag.sound2text.uicommon.di.uiCommonModule
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(
                pdfAndroidModule,
                sttAndroidModule,
                uiCommonModule
            )
        }
        val navigator: AppNavigator =
            org.koin.java.KoinJavaComponent.get(AppNavigator::class.java)
        val sttViewModel: SttViewModel = getViewModel()
        val pdfViewModel: PdfViewModel = getViewModel()
        setContent {
            AppRoot(
                navigator = navigator,
                sttViewModel = sttViewModel,
                pdfViewModel = pdfViewModel,
                renderPdfScreen = { path, page, vm ->
                    AndroidPdfScreen(path, page, vm)
                }
            )
        }
    }
}


/*
// Thin MainActivity using appcommon
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settings = SettingsLoader.load()
        startKoin {
                    androidContext(this@MainActivity)
                    modules(androidModule(settings))
        }
        setContent {
            com.sildeag.sound2text.appcommon.runApp()
        }
    }
}
*/
/*
// Original ManiActivity using :ui-android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsLoader.// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = this
        val settings: AppSettings = SettingsLoader.load()
        startKoin {
            android// TODO: inject platform // TODO: inject platform // TODO: inject platform context
I: Context(this@MainActivity)
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
