package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.uicommon.mappers.*
val mapperModule = module {
    factory<PdfUiMapper> { DefaultPdfUiMapper() }
    factory<TranscriptUiMapper> { TranscriptUiMapper() }
    factory<RecordingUiMapper> { RecordingUiMapper() }
}
