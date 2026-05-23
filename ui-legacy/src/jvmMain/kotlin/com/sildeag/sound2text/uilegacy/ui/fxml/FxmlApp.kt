package com.sildeag.sound2text.uilegacy.ui.fxml

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.java.KoinJavaComponent.getKoin

class FxmlApp : Application() {
    annotation class FxmlLauncher

    override fun start(primaryStage: Stage) {
        try {
            val fxmlUrl = javaClass.getResource("/fxml/main.fxml")
            requireNotNull(fxmlUrl) { "FXML file not found at fxml/main.fxml" }

            val loader = FXMLLoader(fxmlUrl)
            loader.setController(getKoin().get<SoundScreen>())
            val scene = Scene(loader.load())
            primaryStage.title = "Sound2Text"
            primaryStage.scene = scene
            primaryStage.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}