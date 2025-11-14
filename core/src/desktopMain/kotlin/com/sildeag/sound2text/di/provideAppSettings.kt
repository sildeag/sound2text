package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.Environment

fun provideAppSettings(env: Environment): AppSettings {
    return when (env) {
        Environment.DEV -> AppSettings(
            soundDirectory = "resources/sounds/",
            stylePath = "resources/styles/main.css",
            defaultLanguage = "en",
            enableLogging = true
        )
        Environment.PROD -> AppSettings(
            soundDirectory = "/opt/app/sounds/",
            stylePath = "/opt/app/styles/main.css",
            defaultLanguage = "en",
            enableLogging = false
        )
        Environment.TEST -> AppSettings(
            soundDirectory = "test-resources/sounds/",
            stylePath = "test-resources/styles/test.css",
            defaultLanguage = "en",
            enableLogging = true
        )
    }
}
