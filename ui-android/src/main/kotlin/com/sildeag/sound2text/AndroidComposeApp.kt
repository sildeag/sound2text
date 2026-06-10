package com.sildeag.sound2text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.SpeechToTextService
import com.sildeag.sound2text.ui.SharedTheme
@Composable
fun AndroidComposeApp(
    storage: StorageService,
    stt: SpeechToTextService,
    settings: AppSettings,
    logger: Logger
) {
    SharedTheme {
        Text("Sound2Text Android Running (stub UI)")
    }
}
