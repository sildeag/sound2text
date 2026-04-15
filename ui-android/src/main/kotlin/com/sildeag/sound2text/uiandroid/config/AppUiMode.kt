package com.sildeag.sound2text.uiandroid.config

import org.yaml.snakeyaml.Yaml
import java.io.File
import kotlin.text.get

enum class UiMode {
    DesktopCompose,
    DesktopFxml,
    Android,
    Headless
}


object AppUiMode {
    val current: UiMode by lazy {
        val cliMode = System.getProperty("ui")?.uppercase()
        val yamlMode = loadYamlUiMode()
        UiMode.valueOf(cliMode ?: yamlMode ?: "DesktopCompose")
    }

    private fun loadYamlUiMode(): String? {
        return try {
            val yaml = Yaml()
            val config = yaml.load<Map<String, String>>(File("config.yaml").inputStream())
            config["uiMode"]
        } catch (e: Exception) {
            null
        }
    }
}
