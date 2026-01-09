package com.sildeag.sound2text.config

import java.io.File
import org.yaml.snakeyaml.Yaml

enum class Environment { DEV, PROD, TEST }
object AppEnvironment {
    val current: Environment by lazy {
        val cliEnv = System.getProperty("env")?.uppercase()
        val yamlEnv = loadYamlEnvironment()
        Environment.valueOf(cliEnv ?: yamlEnv ?: "PROD")
    }
    private fun loadYamlEnvironment(): String? {
        return try {
            val yaml = Yaml()
            val config = yaml.load<Map<String,
                    String>>(File("config.yaml").inputStream())
            config["environment"]
        } catch (e: Exception) {
            null
        }
    }
    val isProd get() = current == Environment.PROD
    val isDev get() = current == Environment.DEV
    val isTest get() = current == Environment.TEST
}

