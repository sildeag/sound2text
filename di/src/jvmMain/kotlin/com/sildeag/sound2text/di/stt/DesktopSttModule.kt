package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEngineFactory
import com.sildeag.sound2text.sttdesktop.engine.vosk.VoskEngineFactory
import org.koin.dsl.module


fun desktopSttModule(config: SttConfig) = module {
    single { config }
    single<SttEngineFactory> { VoskEngineFactory() }
    single<SttEngine> { get<SttEngineFactory>().load(get()) as SttEngine }
}
fun desktopSttModule(config: SttConfig) = module {
    single { config }
    single { VoskModelFactory(get()).load(config) }
    single<SttService> { VoskSttService(get(), get()) }
}