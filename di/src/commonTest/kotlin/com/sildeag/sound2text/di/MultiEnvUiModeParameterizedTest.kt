package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.*
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class MultiEnvUiModeParameterizedTest : KoinTest {
    private val appEnv: AppEnvironment by inject()
    private val appUiMode: AppUiMode by inject()

    @TestFactory
    fun environmentUiModeMatrix(): Collection<DynamicTest> {
        return allCases.map { case ->
            DynamicTest.dynamicTest(
                "Env=${case.expectedEnv}, Ui=${case.expectedUi}") {
                startKoin {
                    modules(harnessModule(case.config))
                }
                assertEquals(case.expectedEnv, appEnv.current)
                assertEquals(case.expectedUi, appUiMode.current)
            }
        }
    }
}