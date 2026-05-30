package com.sildeag.sound2text.di.settings

import kotlinx.serialization.json.Json
import kotlin.runCatching
import android.content.Context
import android.content.SharedPreferences
import com.sildeag.sound2text.core.logging.LogLevel
import com.sildeag.sound2text.core.settings.SettingsStore
import com.sildeag.sound2text.core.config.AppMode
import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.AudioSettings
import com.sildeag.sound2text.core.config.EngineConfig
import com.sildeag.sound2text.core.config.LoggingConfig
import com.sildeag.sound2text.core.config.ServiceSettings
import com.sildeag.sound2text.core.config.SpeechToTextSettings
import com.sildeag.sound2text.core.config.SttLanguage
import com.sildeag.sound2text.core.config.SttOutputFormat
import com.sildeag.sound2text.core.config.SttProvider
import com.sildeag.sound2text.core.config.ThemeConfig
import com.sildeag.sound2text.core.config.ThemeMode
import com.sildeag.sound2text.core.config.UiSettings

class AndroidSettingsStore(context: Context) : SettingsStore {
    private val prefs = context.getSharedPreferences("sound2text",
        Context.MODE_PRIVATE)
    override fun load(): AppSettings {
        return AppSettings(
            mode = prefs.enum("mode", AppMode.DEV),
            ui = UiSettings(
                type = prefs.getString("ui.type", "Compose")!!,
                lastScreen = prefs.getString(
                    "ui.lastScreen",
                    "Home"
                )!!,
                windowWidth = prefs.getInt("ui.windowWidth", 1200),
                windowHeight = prefs.getInt("ui.windowHeight", 800),
                sidebarExpanded =
                    prefs.getBoolean("ui.sidebarExpanded", true)
            ),
            audio = AudioSettings(
                inputDevice = prefs.getString(
                    "audio.inputDevice",
                    "default"
                )!!,
                outputDevice = prefs.getString(
                    "audio.outputDevice",
                    "default"
                )!!,
                sampleRate = prefs.getInt("audio.sampleRate", 16000),
                channels = prefs.getInt("audio.channels", 1),
                bitDepth = prefs.getInt("audio.bitDepth", 16),
                normalize = prefs.getBoolean(
                    "audio.normalize",
                    true
                ),
                noiseSuppression =
                    prefs.getBoolean("audio.noiseSuppression", true),
                autoGain = prefs.getBoolean("audio.autoGain", false),
                bufferSize = prefs.getInt("audio.bufferSize", 4096),
                soundFiles = prefs.getString(
                    "audio.soundFiles",
                    ""
                )!!
                    .takeIf { it.isNotBlank() }
                    ?.split(";") ?: emptyList()
            ),
            stt = SpeechToTextSettings(
                provider = prefs.enum(
                    "stt.provider",
                    SttProvider.Vosk
                ),
                language = prefs.enum(
                    "stt.language",
                    SttLanguage.English
                ),
                outputFormat = prefs.enum(
                    "stt.outputFormat",
                    SttOutputFormat.Plain
                ),
                model = prefs.getString("stt.model", "base")!!,
                apiKey = prefs.getString("stt.apiKey", "")!!,
                endpoint = prefs.getString("stt.endpoint", "")!!,
                engine = prefs.json("stt.engine", EngineConfig())
            ),
            services = ServiceSettings(
                baseUrl = prefs.getString("services.baseUrl", "")!!,
                timeoutSeconds =
                    prefs.getInt("services.timeoutSeconds", 30),
                retryCount = prefs.getInt("services.retryCount", 3),
                apiVersion = prefs.getString(
                    "services.apiVersion",
                    "v1"
                )!!
            ),
            theme = ThemeConfig(
                mode = prefs.enum("theme.mode", ThemeMode.Light),
                primary = prefs.getString(
                    "theme.primary",
                    "#FFFFFF"
                )!!,
                accent = prefs.getString(
                    "theme.accent",
                    "#448AFF"
                )!!,
                highContrast = prefs.getBoolean(
                    "theme.highContrast",
                    false
                )
            ),
            logging = LoggingConfig(
                enabled = prefs.getBoolean("logging.enabled", true),
                level = prefs.enum("logging.level", LogLevel.Info),
                filePath = prefs.getString("logging.filePath", "")!!,
                maxFileSizeKb = prefs.getInt(
                    "logging.maxFileSizeKb",
                    1024
                ),
                rotate = prefs.getBoolean("logging.rotate", true)
            )
        )
    }

    override fun save(settings: AppSettings) {
        prefs.edit().apply {
            putString("mode", settings.mode.name)
            putString("ui.type", settings.ui.type)
            putString("ui.lastScreen", settings.ui.lastScreen)
            putInt("ui.windowWidth", settings.ui.windowWidth)
            putInt("ui.windowHeight", settings.ui.windowHeight)
            putBoolean("ui.sidebarExpanded",
                settings.ui.sidebarExpanded)
            putString("audio.inputDevice",
                settings.audio.inputDevice)
            putString("audio.outputDevice",
                settings.audio.outputDevice)
            putInt("audio.sampleRate", settings.audio.sampleRate)
            putInt("audio.channels", settings.audio.channels)
            putInt("audio.bitDepth", settings.audio.bitDepth)
            putBoolean("audio.normalize", settings.audio.normalize)
            putBoolean("audio.noiseSuppression",
                settings.audio.noiseSuppression)
            putBoolean("audio.autoGain", settings.audio.autoGain)
            putInt("audio.bufferSize", settings.audio.bufferSize)
            putString("audio.soundFiles",
                settings.audio.soundFiles.joinToString(";"))
            putString("stt.provider", settings.stt.provider.name)
            putString("stt.language", settings.stt.language.name)
            putString("stt.outputFormat",
                settings.stt.outputFormat.name)
            putString("stt.model", settings.stt.model)
            putString("stt.apiKey", settings.stt.apiKey)
            putString("stt.endpoint", settings.stt.endpoint)
            putString("stt.engine",
                Json.encodeToString(settings.stt.engine))
            putString("services.baseUrl", settings.services.baseUrl)
            putInt("services.timeoutSeconds",
                settings.services.timeoutSeconds)
            putInt("services.retryCount",
                settings.services.retryCount)
            putString("services.apiVersion",
                settings.services.apiVersion)
            putString("theme.mode", settings.theme.mode.name)
            putString("theme.primary", settings.theme.primary)
            putString("theme.accent", settings.theme.accent)
            putBoolean("theme.highContrast",
                settings.theme.highContrast)
            putBoolean("logging.enabled", settings.logging.enabled)
            putString("logging.level", settings.logging.level.name)
            putString("logging.filePath", settings.logging.filePath)
            putInt("logging.maxFileSizeKb",
                settings.logging.maxFileSizeKb)
            putBoolean("logging.rotate", settings.logging.rotate)
        }.apply()
    }
    private inline fun <reified T : Enum<T>> SharedPreferences.enum(
        key: String,
        default: T
    ): T = runCatching {
        getString(key, default.name)!!.let { enumValueOf<T>(it) }
    }.getOrElse { default }
    private inline fun <reified T> SharedPreferences.json(
        key: String,
        default: T
    ): T = runCatching {
        Json.decodeFromString<T>(getString(key, "")!!)
    }.getOrElse { default }
}

/*
import android.content.// TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.core.config.AppSettings

class AndroidSettingsStore(// TODO: inject platform context
via DI: Context: // TODO: inject platform context
via DI: Context) : SettingsStore {
    private val prefs = // TODO: inject platform context
via DI: Context.get// TODO: move to
platform storage + DI: SharedPreferences("sound2text",
        // TODO: inject platform context
via DI: Context.MODE_PRIVATE)
    override fun load(): AppSettings =
        AppSettings(
            platform = "Android",
            mode = prefs.getString("mode", "DEV") ?: "DEV",
            ui = UiSettings(
                type = prefs.getString("ui.type", "Fxml") ?: "Fxml"
            ),
            audio = AudioSettings(
                input = prefs.getString("audio.input", "") ?: "",
                sampleRate = prefs.getInt("audio.sampleRate", 16000),
                channels = prefs.getString(
                    "audio.channels",
                    "Auto"
                ) ?: "Auto",
                bitDepth = prefs.getInt("audio.bitDepth", 1),
                normalize = prefs.getBoolean(
                    "audio.normalize",
                    true
                ),
                downmixToMono =
                    prefs.getBoolean("audio.downmixToMono", true),
                inputDevice = prefs.getString(
                    "audio.inputDevice",
                    "default"
                ) ?: "default",
                outputDevice = prefs.getString(
                    "audio.outputDevice",
                    "default"
                ) ?: "default",
                soundFiles = (prefs.getString("audio.soundFiles", "")
                    ?: "")
                    .split("|").filter { it.isNotBlank() }
            ),
            stt = SpeechToTextSettings(
                provider =
                    SttProvider.valueOf(prefs.getString("stt.provider", "Vosk")!!),
                language =
                    SttLanguage.valueOf(prefs.getString("stt.language", "English")!!),
                outputFormat =
                    SttOutputFormat.valueOf(
                        prefs.getString(
                            "stt.outputFormat",
                            "Plain"
                        )!!
                    ),
                engineConfig = null,
                apiKey = prefs.getString("stt.apiKey", "") ?: "",
                endpoint = prefs.getString("stt.endpoint", "") ?: "",
                model = prefs.getString("stt.model", "base") ?: "base"
            ),
            services = ServiceSettings(
                // fill in your service fields here
            ),
            theme = ThemeConfig(
                // fill in your theme fields here
            ),
            logging = LoggingConfig(
                // fill in your logging fields here
            )
        )
    override fun save(settings: AppSettings) {
        prefs.edit().apply {
            putString("mode", settings.mode)
            putString("ui.type", settings.ui.type)
            putString("audio.input", settings.audio.input)
            putInt("audio.sampleRate", settings.audio.sampleRate)
            putString("audio.channels", settings.audio.channels)
            putInt("audio.bitDepth", settings.audio.bitDepth)
            putBoolean("audio.normalize", settings.audio.normalize)
            putBoolean("audio.downmixToMono",
                settings.audio.downmixToMono)
            putString("audio.inputDevice",
                settings.audio.inputDevice)
            putString("audio.outputDevice",
                settings.audio.outputDevice)
            putString("audio.soundFiles",
                settings.audio.soundFiles.joinToString("|"))
            putString("stt.provider", settings.stt.provider.name)
            putString("stt.language", settings.stt.language.name)
            putString("stt.outputFormat",
                settings.stt.outputFormat.name)
            putString("stt.apiKey", settings.stt.apiKey)
            putString("stt.endpoint", settings.stt.endpoint)
            putString("stt.model", settings.stt.model)
            // theme, logging, services saved here
            apply()
        }
    }
}

 */
