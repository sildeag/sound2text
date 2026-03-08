package com.sildeag.sound2text.uidesktop.harness

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.test.KoinTestHarness
import com.sildeag.sound2text.`desktop-ui`.ui.SoundScreen
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.java.KoinJavaComponent
import org.koin.test.KoinTest

class FullLifecycleTest : com.sildeag.sound2text.test.KoinTestHarness(
    environment = Environment.PROD,
    moduleOverrides = {}
), KoinTest {
    fun start(primaryStage: Stage) {
        try {
            val fxmlUrl = javaClass.getResource("fxml/main.fxml")
            requireNotNull(fxmlUrl) { "FXML file not found at /fxml/main.fxml" }

            val loader = FXMLLoader(fxmlUrl)
            loader.setController(KoinJavaComponent.getKoin().get<SoundScreen>())
            val scene = Scene(loader.load())
            primaryStage.title = "Sound2Text"
            primaryStage.scene = scene
            primaryStage.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}