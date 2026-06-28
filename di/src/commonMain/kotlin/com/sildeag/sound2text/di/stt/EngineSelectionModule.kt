package com.sildeag.sound2text.di.stt

package com.sildeag.sound2text.di.stt
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.CombinedEngine
import org.koin.core.qualifier.named
import org.koin.dsl.module
object EngineQualifiers {
    val Vosk = named("vosk")
    val Whisper = named("whisper")
    val Combined = named("combined")
}
val engineSelectionModule = module {
    // Combined engine (meta-engine)
    single<SttEngine>(EngineQualifiers.Combined) {
        CombinedEngine(
            fastEngine = get(EngineQualifiers.Vosk),
            accurateEngine = get(EngineQualifiers.Whisper)
        )
    }
    // Selected engine (based on settings)
    single<SttEngine> {
        val settings = get<SettingsStore>()
        when (settings.engineType()) {
            EngineType.Vosk -> get(EngineQualifiers.Vosk)
            EngineType.Whisper -> get(EngineQualifiers.Whisper)
            EngineType.Combined -> get(EngineQualifiers.Combined)
        }
    }
}