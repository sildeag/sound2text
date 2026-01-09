package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppUiMode
import com.sildeag.sound2text.config.UiMode

val desktopModule = module {
    single<UiLauncher> {
        when (AppUiMode.current) {
            UiMode.DesktopCompose -> ComposeUiLauncher()
            UiMode.DesktopFxml    -> FxmlUiLauncher()
        }
    }
}