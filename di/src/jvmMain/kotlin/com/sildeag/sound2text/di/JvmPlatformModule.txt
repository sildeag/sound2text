package com.sildeag.sound2text.di

class JvmPlatformModuleval jvmPlatformModule = module {
    single { DesktopLogger() }
    single { FileSettingsStore(get()) }
    single { JvmAudioEngine() }
    single { JdbcSQLiteDriver(get()) }
    single { DesktopDeviceCapabilityProvider() }
} {
}