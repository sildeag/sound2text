package com.sildeag.sound2text.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
/**
 * Shared dispatcher provider for KMP.
 * Implemented separately on each platform.
 */
interface DispatcherProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}