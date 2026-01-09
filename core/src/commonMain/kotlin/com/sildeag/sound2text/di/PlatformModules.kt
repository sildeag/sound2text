package com.sildeag.sound2text.di

expect fun platformModules(): List<Module>
expect class PlatformEnvironment {
    val environment: String
    val isDebug: Boolean
}
