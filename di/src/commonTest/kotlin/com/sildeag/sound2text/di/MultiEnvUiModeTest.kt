package com.sildeag.sound2text.di

import com.sildeag.sound2text.pdfdesktop.*
import org.koin.core.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
class MultiEnvUiModeTest : KoinTest {
    private val appEnv: AppEnvironment by inject()
    private val appUiMode: AppUiMode by inject()
    private fun runHarness(config: Config, expectedEnv: Environment,
                           expectedUi: UiMode) {
        startKoin { modules(harnessModule(config)) }
        assertEquals(expectedEnv, appEnv.current)
        assertEquals(expectedUi, appUiMode.current)
    }
    @Test fun testDevCompose() = runHarness(fakeConfigDevCompose,
        Environment.DEV, UiMode.DesktopCompose)
    @Test fun testDevFxml() = runHarness(fakeConfigDevFxml,
        Environment.DEV, UiMode.DesktopFxml)
    @Test fun testProdCompose() = runHarness(fakeConfigProdCompose,
        Environment.PROD, UiMode.DesktopCompose)
    @Test fun testProdFxml() = runHarness(fakeConfigProdFxml,
        Environment.PROD, UiMode.DesktopFxml)
    @Test fun testTestCompose() = runHarness(fakeConfigTestCompose,
        Environment.TEST, UiMode.DesktopCompose)
    @Test fun testTestFxml() = runHarness(fakeConfigTestFxml,
        Environment.TEST, UiMode.DesktopFxml)
}
