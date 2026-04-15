package com.sildeag.sound2text.uilegacy

import com.sildeag.sound2text.uilegacy.viewmodel.LegacyViewModel
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage
class LegacyApp : Application() {
    override fun start(stage: Stage) {
        val reducer = AppReducer() // from :core
        val initialState = AppState() // from :core
        val viewModel = LegacyViewModel(reducer, initialState)
        val root = LegacyView(viewModel) // your JavaFX UI
        //val root = StackPane(
        //    Label("Sound2Text Legacy UI")
        //)
        stage.title = "Sound2Text (Legacy JavaFX)"
        //stage.scene = Scene(root)
        stage.scene = Scene(root, 800.0, 600.0)
        stage.show()
    }
}