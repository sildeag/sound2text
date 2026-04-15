package com.sildeag.sound2text.platform

import android.content.Context
import com.sildeag.sound2text.uiandroid.config.AppSettings
import kotlinx.serialization.json.Json

actual object SettingsLoader {
    private lateinit var appContext: Context
    fun init(context: Context) {
        appContext = context.applicationContext
    }
    actual fun load(): AppSettings {
        val json = Json { ignoreUnknownKeys = true }
        val input = appContext.assets.open("config.json")
        val text = input.bufferedReader().use { it.readText() }
        return json.decodeFromString(AppSettings.serializer(), text)
    }
}