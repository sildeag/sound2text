package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.UiSettings


val fakeConfigDevCompose = AppSettings(mode = "DEV", ui = UiSettings("compose"),
    , defaultLanguage = "en", assets = Assets(...))
val fakeConfigDevFxml = fakeConfigDevCompose.copy(uiMode = "fxml")
val fakeConfigProdCompose = fakeConfigDevCompose.copy(environment =
    "PROD", uiMode = "compose")
val fakeConfigProdFxml = fakeConfigDevCompose.copy(environment =
    "PROD", uiMode = "fxml")
val fakeConfigTestCompose = fakeConfigDevCompose.copy(environment =
    "TEST", uiMode = "compose")
val fakeConfigTestFxml = fakeConfigDevCompose.copy(environment =
    "TEST", uiMode = "fxml")