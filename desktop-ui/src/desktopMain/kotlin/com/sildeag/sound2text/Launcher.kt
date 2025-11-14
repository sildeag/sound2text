package com.sildeag.sound2text

import javafx.application.Application
import com.sildeag.sound2text.config.AppEnvironment
import com.sildeag.sound2text.config.AppUiMode
import com.sildeag.sound2text.config.UiMode
import org.koin.core.context.startKoin
import com.sildeag.sound2text.di.loadEnvironmentModules

private val AppEnvironment.Companion.DesktopCompose: Any

fun main() {
    startKoin {
        printLogger()
        modules(loadEnvironmentModules())
    }
    println("Launching in ${AppEnvironment.current} environment")
    println("UI mode: ${AppUiMode.current}")
    when (AppUiMode.current) {
        UiMode.DesktopCompose -> ComposeApp.launch()
        UiMode.DesktopFxml ->
            Application.launch(Launcher::class.java)
        else -> error("Unsupported UI mode: ${AppUiMode.current}")
    }
}

