package com.sildeag.sound2text.fxml

import com.sildeag.sound2text.ui.SoundScreen
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.java.KoinJavaComponent.getKoin

class FxmlApp : Application() {
    override fun start(primaryStage: Stage) {
        try {
            val fxmlUrl = javaClass.getResource("view/sound_screen.fxml")
            requireNotNull(fxmlUrl) { "FXML file not found at /view/sound_screen.fxml" }

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