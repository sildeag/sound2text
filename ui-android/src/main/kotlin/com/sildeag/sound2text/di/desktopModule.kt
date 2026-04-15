package com.sildeag.sound2text.di

import com.sildeag.sound2text.uiandroid.config.AppUiMode
import com.sildeag.sound2text.uiandroid.config.UiMode
import com.sildeag.sound2text.ui.ComposeUiLauncher
import com.sildeag.sound2text.ui.FxmlUiLauncher
import com.sildeag.sound2text.ui.UiLauncher
import org.koin.dsl.module

val desktopModule = module {
    single<UiLauncher> {
        when (AppUiMode.current) {
            UiMode.DesktopCompose -> ComposeUiLauncher()
            UiMode.DesktopFxml    -> FxmlUiLauncher()
            UiMode.Android        -> error("Cannot create Desktop UI launcher in Android mode")
            UiMode.Headless       -> error("Cannot create Desktop UI launcher in Headless mode")
        }
    }
}