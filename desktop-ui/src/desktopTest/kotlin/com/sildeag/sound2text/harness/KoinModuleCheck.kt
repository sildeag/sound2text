package com.sildeag.sound2text.harness

import com.sildeag.sound2text.di.loadEnvironmentModules
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import kotlin.test.Test


class KoinModuleCheck {
    @Test
    fun `verify all my modules`() {
        koinApplication {
            modules(loadEnvironmentModules())
            checkModules()
        }
    }
}
