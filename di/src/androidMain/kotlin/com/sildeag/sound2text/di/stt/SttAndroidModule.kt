package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidSttService
import org.koin.dsl.module
import android.content.// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import org.koin.core.module.Module

fun androidSttModule(// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, config: SttConfig): Module = module {
    single { config }
    single {
        VoskAndroidModelFactory.loadModel(// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, get())
    }
    single<SttService> {
        VoskAndroidSttService(
            model = get(),
            config = get()
        )
    }
}

/*
import com.sildeag.sound2text.stt.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vosk.android.SpeechService
import org.vosk.android.Recognizer
import org.vosk.Model

val SttAndroidModule = module {
    // --- Engine registrations -------------------------------------------------
    // Vosk engine for Android
    single<SttEngine>(named("vosk")) {
        val config = get<SttConfig>()
        val model = Model(config.modelPath)
        VoskAndroidSttEngine(model) // your Android engine class
    }
    // Whisper-CPP engine placeholder
    single<SttEngine>(named("whisper")) {
        WhisperCppAndroidSttEngine() // implement later
    }
    // --- Platform STT service -------------------------------------------------
    // AndroidSttService delegates to whichever engine is selected
    factory<SttService> {
        val config = get<SttConfig>()
        val engineName = config.engineName
        val engine = get<SttEngine>(named(engineName))
        AndroidSttService(engine, config)
    }
}
 */
/*
import com.sildeag.sound2text.core.stt.*
// TODO: remove engine
import.CreateSttAndroid
// TODO: remove engine
import.vosk.VoskPlugin
// TODO: remove engine
import.whisper.WhisperPlugin
import com.sildeag.sound2text.sttandroid.service.AndroidSttService
import org.koin.dsl.module

val androidSttModule = module {
    // 1. Register Android STT plugins
    single<List<SttEnginePlugin>> {
        listOf(
            VoskPlugin(),
            WhisperPlugin()
        )
    }
    // 2. Android-specific STT orchestrator
    single { CreateSttAndroid(get()) }
    // 3. Provide Android STT service
    single<SttService> {
        val config = get<SttConfig>()
        val createStt = get<CreateSttAndroid>()
        createStt.create(config)
    }
    // 4. Android audio capture (optional)
    // single { AndroidAudioCapture(// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = get()) }
}
 */

/*
// TODO: remove engine
import
// TODO: remove engine
import
import org.koin.core.module.Module
import org.koin.dsl.module

val sttAndroidModule: Module = module {
    single<SttEngine> { AndroidSttEngine() }
}
*/
