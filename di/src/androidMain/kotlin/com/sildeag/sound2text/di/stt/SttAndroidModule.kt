package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidSttService
import org.koin.dsl.module
import android.content.Context
import org.koin.core.module.Module

fun androidSttModule(context: Context, config: SttConfig): Module = module {
    single { config }
    single {
        VoskAndroidModelFactory.loadModel(context, get())
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
import com.sildeag.sound2text.sttandroid.engine.CreateSttAndroid
import com.sildeag.sound2text.sttandroid.engine.vosk.VoskPlugin
import com.sildeag.sound2text.sttandroid.engine.whisper.WhisperPlugin
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
    // single { AndroidAudioCapture(context = get()) }
}
 */

/*
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.sttandroid.AndroidSttEngine
import org.koin.core.module.Module
import org.koin.dsl.module

val sttAndroidModule: Module = module {
    single<SttEngine> { AndroidSttEngine() }
}

