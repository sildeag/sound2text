package com.sildeag.sound2text.di.stt

import android.content.Context
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEngineFactory
import com.sildeag.sound2text.sttandroid.engine.vosk.VoskAndroidEngineFactory
import org.koin.dsl.module
fun androidSttModule(context: Context, config: SttConfig) = module {
    // Provide config
    single { config }
    // Provide engine factory
    single<SttEngineFactory> { VoskAndroidEngineFactory(context) }
    // Provide engine
    single<SttEngine> { get<SttEngineFactory>().load(get()) }
}
