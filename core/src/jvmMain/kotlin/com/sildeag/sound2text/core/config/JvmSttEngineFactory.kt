import com.sildeag.sound2text.pdf.SpeechToTextSettings
import com.sildeag.sound2text.pdf.SttProvider
import com.sildeag.sound2text.pdf.VoskEngine

class JvmSttEngineFactory : SttEngineFactory {
    override fun create(settings: SpeechToTextSettings): SttEngine {
        val engineConfig = settings.engineConfig ?: SttEngineConfig()
        return when (settings.provider) {
            SttProvider.Vosk ->
                VoskEngine(
                    modelPath = settings.model,
                    sampleRate = 16000,
                    maxAlternatives = 0,
                    enableWords = true,
                    enablePartialResults = true,
                    logLevel = 0,
                    language = settings.language,
                    ffmpeg = engineConfig.ffmpeg
                )
            SttProvider.Whisper ->
                WhisperEngine(
                    model = settings.model,
                    language = settings.language,
                    ffmpeg = engineConfig.ffmpeg
                )
            SttProvider.Azure ->
                AzureSttEngine(
                    apiKey = settings.apiKey,
                    endpoint = settings.endpoint,
                    language = settings.language
                )
            SttProvider.Google ->
                GoogleSttEngine(
                    apiKey = settings.apiKey,
                    language = settings.language
                )
        }
    }
}


/*
import com.sildeag.sound2text.config.VoskConfig
import com.sildeag.sound2text.config.WhisperConfig
import com.sildeag.sound2text.config.SttEngine


class JvmSttEngineFactory : SttEngineFactory {
    override fun create(settings: SpeechToTextSettings): SttEngine {
        val cfg: SttEngineConfig = when (settings.provider) {
            SttProvider.Vosk -> {
                (settings.engineConfig as? VoskConfig)
                    ?: VoskConfig.defaultFor(settings.language)
            }
            SttProvider.Whisper -> {
                (settings.engineConfig as? WhisperConfig)
                    ?: WhisperConfig.defaultFor(settings.language)
            }
            SttProvider.Azure -> AzureConfig(
                apiKey = settings.apiKey,
                endpoint = settings.endpoint,
                language = settings.language
            )
            SttProvider.Google -> GoogleConfig(
                apiKey = settings.apiKey,
                language = settings.language
            )
        }
        return when (settings.provider) {
            SttProvider.Vosk -> VoskEngine(cfg as VoskConfig)
            SttProvider.Whisper -> WhisperEngine(cfg as WhisperConfig)
            SttProvider.Azure -> AzureSttEngine(cfg as AzureConfig)
            SttProvider.Google -> GoogleSttEngine(cfg as GoogleConfig)
        }
    }
}
*/