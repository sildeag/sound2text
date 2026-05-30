package com.sildeag.sound2text.uidesktop.settings

import com.sildeag.sound2text.core.config.*
import com.sildeag.sound2text.core.settings.SettingsStore
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.prefs.Preferences

class DesktopSettingsStore : SettingsStore {
    private val prefs = Preferences.userRoot().node("sound2text")
    override fun load(): AppSettings {
        return AppSettings(
            mode = prefs.enum("mode", AppMode.DEV),
            ui = UiSettings(
                type = prefs.get("ui.type", "Compose"),
                lastScreen = prefs.get("ui.lastScreen", "Home"),
                windowWidth = prefs.getInt("ui.windowWidth", 1200),
                windowHeight = prefs.getInt("ui.windowHeight", 800),
                sidebarExpanded =
                    prefs.getBoolean("ui.sidebarExpanded", true)
            ),
            audio = AudioSettings(
                inputDevice = prefs.get("audio.inputDevice",
                    "default"),
                outputDevice = prefs.get("audio.outputDevice",
                    "default"),
                sampleRate = prefs.getInt("audio.sampleRate", 16000),
                channels = prefs.getInt("audio.channels", 1),
                bitDepth = prefs.getInt("audio.bitDepth", 16),
                normalize = prefs.getBoolean("audio.normalize",
                    true),
                noiseSuppression =
                    prefs.getBoolean("audio.noiseSuppression", true),
                autoGain = prefs.getBoolean("audio.autoGain", false),
                bufferSize = prefs.getInt("audio.bufferSize", 4096),
                soundFiles = prefs.get("audio.soundFiles", "")
                    .takeIf { it.isNotBlank() }
                    ?.split(";") ?: emptyList()
            ),
            stt = SpeechToTextSettings(
                provider = prefs.enum("stt.provider",
                    SttProvider.Vosk),
                language = prefs.enum("stt.language",
                    SttLanguage.English),
                outputFormat = prefs.enum("stt.outputFormat",
                    SttOutputFormat.Plain),
                model = prefs.get("stt.model", "base"),
                apiKey = prefs.get("stt.apiKey", ""),
                endpoint = prefs.get("stt.endpoint", ""),
                engine = prefs.json("stt.engine", EngineConfig())
            ),
            services = ServiceSettings(
                baseUrl = prefs.get("services.baseUrl", ""),
                timeoutSeconds =
                    prefs.getInt("services.timeoutSeconds", 30),
                retryCount = prefs.getInt("services.retryCount", 3),
                apiVersion = prefs.get("services.apiVersion", "v1")
            ),
            theme = ThemeConfig(
                mode = prefs.enum("theme.mode", ThemeMode.Light),
                primary = prefs.get("theme.primary", "#FFFFFF"),
                accent = prefs.get("theme.accent", "#448AFF"),
                highContrast = prefs.getBoolean("theme.highContrast",
                    false)
            ),
            logging = LoggingConfig(
                enabled = prefs.getBoolean("logging.enabled", true),
                level = prefs.enum("logging.level", LogLevel.Info),
                filePath = prefs.get("logging.filePath", ""),
                maxFileSizeKb = prefs.getInt("logging.maxFileSizeKb",
                    1024),
                rotate = prefs.getBoolean("logging.rotate", true)
            )
        )
    }
    override fun save(settings: AppSettings) {
        prefs.put("mode", settings.mode.name)
        prefs.put("ui.type", settings.ui.type)
        prefs.put("ui.lastScreen", settings.ui.lastScreen)
        prefs.putInt("ui.windowWidth", settings.ui.windowWidth)
        prefs.putInt("ui.windowHeight", settings.ui.windowHeight)
        prefs.putBoolean("ui.sidebarExpanded",
            settings.ui.sidebarExpanded)
        prefs.put("audio.inputDevice", settings.audio.inputDevice)
        prefs.put("audio.outputDevice", settings.audio.outputDevice)
        prefs.putInt("audio.sampleRate", settings.audio.sampleRate)
        prefs.putInt("audio.channels", settings.audio.channels)
        prefs.putInt("audio.bitDepth", settings.audio.bitDepth)
        prefs.putBoolean("audio.normalize", settings.audio.normalize)
        prefs.putBoolean("audio.noiseSuppression",
            settings.audio.noiseSuppression)
        prefs.putBoolean("audio.autoGain", settings.audio.autoGain)
        prefs.putInt("audio.bufferSize", settings.audio.bufferSize)
        prefs.put("audio.soundFiles",
            settings.audio.soundFiles.joinToString(";"))
        prefs.put("stt.provider", settings.stt.provider.name)
        prefs.put("stt.language", settings.stt.language.name)
        prefs.put("stt.outputFormat", settings.stt.outputFormat.name)
        prefs.put("stt.model", settings.stt.model)
        prefs.put("stt.apiKey", settings.stt.apiKey)
        prefs.put("stt.endpoint", settings.stt.endpoint)
        prefs.put("stt.engine",
            Json.encodeToString(settings.stt.engine))
        prefs.put("services.baseUrl", settings.services.baseUrl)
        prefs.putInt("services.timeoutSeconds",
            settings.services.timeoutSeconds)
        prefs.putInt("services.retryCount",
            settings.services.retryCount)
        prefs.put("services.apiVersion",
            settings.services.apiVersion)
        prefs.put("theme.mode", settings.theme.mode.name)
        prefs.put("theme.primary", settings.theme.primary)
        prefs.put("theme.accent", settings.theme.accent)
        prefs.putBoolean("theme.highContrast",
            settings.theme.highContrast)
        prefs.putBoolean("logging.enabled", settings.logging.enabled)
        prefs.put("logging.level", settings.logging.level.name)
        prefs.put("logging.filePath", settings.logging.filePath)
        prefs.putInt("logging.maxFileSizeKb",
            settings.logging.maxFileSizeKb)
        prefs.putBoolean("logging.rotate", settings.logging.rotate)
    }
    private inline fun <reified T : Enum<T>> Preferences.enum(key:
                                                              String, default: T): T =
        runCatching { get(key, default.name).let { enumValueOf<T>(it)
        } }.getOrElse { default }
    private inline fun <reified T> Preferences.json(key: String,
                                                    default: T): T =
        runCatching { Json.decodeFromString<T>(get(key,
            "")) }.getOrElse { default }
}
