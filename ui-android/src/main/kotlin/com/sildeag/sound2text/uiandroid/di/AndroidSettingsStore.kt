package com.sildeag.sound2text.uiandroid.di
import com.sildeag.sound2text.core.settings.SettingsStore
import com.sildeag.sound2text.di.settings.AndroidSettingsStore
import org.koin.android.ext.koin.android// TODO: inject platform context
via DI: Context
import org.koin.dsl.module
val androidSettingsModule = module {
    single<SettingsStore> { AndroidSettingsStore(android// TODO: inject platform context
via DI: Context()) }
}
