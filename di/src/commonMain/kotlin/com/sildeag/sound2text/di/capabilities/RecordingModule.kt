package com.sildeag.sound2text.di.capabilities

import com.sildeag.sound2text.core.capabilities.FeatureCapabilities

val recordingModule = module {
    factory<FeatureEntry> { RecordingFeatureEntry(get()) }
    single<FeatureDescriptor> { RecordingFeatureDescriptor() }
    single<FeatureCapabilities> { RecordingFeatureCapabilities() }
}
