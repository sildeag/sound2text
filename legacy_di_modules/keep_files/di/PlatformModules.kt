package com.sildeag.sound2text.di

// This declares the contract for platform modules.

// How you can make Gradle source sets automatically prefer the fake
// actual implementations during tests, so you don’t have to manually swap modules.

import org.koin.core.module.Module
expect fun platformModules(contextProvider: Any? = null): List<Module>