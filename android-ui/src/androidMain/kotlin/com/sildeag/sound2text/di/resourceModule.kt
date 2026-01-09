package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.ResourceLoader
import com.sildeag.sound2text.service.ResourceLoaderImpl
import org.koin.dsl.module

val resourceModule = module {
    single<ResourceLoader> { ResourceLoaderImpl(get()) }
}