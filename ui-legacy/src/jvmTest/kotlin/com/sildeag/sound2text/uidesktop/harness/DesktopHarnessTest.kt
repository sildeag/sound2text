package com.sildeag.sound2text.uidesktop.harness

import com.sildeag.sound2text.pdf.*
import com.sildeag.sound2text.di.initKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
class DesktopHarnessTest : KoinTest {
    private val audioService: AudioService by inject()
    @Test
    fun testDesktopUsesFakeServices() {
        initKoin(environmentOverride = Environment.DEV)
        assertEquals("fake-audio-data", audioService.record())
    }
}
