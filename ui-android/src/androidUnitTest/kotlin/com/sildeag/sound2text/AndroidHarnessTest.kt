package com.sildeag.sound2text

import com.sildeag.sound2text.config.*
import com.sildeag.sound2text.di.initKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
class AndroidHarnessTest : KoinTest {
    private val audioService: AudioService by inject()

    @Test
    fun testAndroidUsesFakeServices() {
        initKoin(
            environmentOverride = Environment.DEV,
            // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: ContextProvider = null
        )
        assertEquals("fake-audio-data", audioService.record())
    }
}
