package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.service.ResourceLoader
import com.sildeag.sound2text.core.service.ResourceLoaderImpl
import org.koin.dsl.module

val resourceModule = module {
    single<ResourceLoader> { ResourceLoaderImpl(get()) }
}