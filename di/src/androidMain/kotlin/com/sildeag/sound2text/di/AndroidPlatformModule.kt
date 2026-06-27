package com.sildeag.sound2text.di

val androidPlatformModule = module {
    single { AndroidLogger() }
    single { AndroidSettingsStore(androidContext()) }
    single { AndroidAudioEngine(androidContext()) }
    single { AndroidSQLiteDriver(androidContext()) }
    single { AndroidDeviceCapabilityProvider(androidContext()) }
}