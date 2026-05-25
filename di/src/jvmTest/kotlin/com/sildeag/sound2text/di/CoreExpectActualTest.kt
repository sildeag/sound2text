package com.sildeag.com.sound2text.di

import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class CoreExpectActualTest : KoinTest {
    private val appEnv: AppEnvironment by inject()
    private val appUiMode: AppUiMode by inject()
    private val audioService: AudioService by inject()
    @Test
    fun testFakePlatformModulesAreUsed() {
        initKoin(environmentOverride = Environment.DEV)
        assertEquals(Environment.DEV, appEnv.current)
        assertEquals(UiMode.DesktopCompose, appUiMode.current)
        assertEquals("fake-audio-data", audioService.record())
    }
}
