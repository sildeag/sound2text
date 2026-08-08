package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.core.audio.*
import com.sildeag.sound2text.core.stt.*
import com.sildeag.sound2text.core.pdf.*
val serviceModule = module {
    single { AudioProcessor() }
    single { SttEngine() }
    single { PdfProcessor() }
    single { PdfBuilder() }
}
