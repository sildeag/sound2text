package com.sildeag.sound2text.di.capabilities

import org.koin.core.module.Module
import org.koin.dsl.module

val featureLoaderModule = module {
    single { FeatureLoader(get(), getAll(), getAll()) }
}
