package com.sildeag.sound2text.test

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.di.loadEnvironmentModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.ModuleDeclaration
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.dsl.module
import org.koin.test.KoinTest

abstract class KoinTestHarness(
    private val environment: Environment = AppEnvironment.current,
    private val moduleOverrides: ModuleDeclaration = {}
) : KoinTest {

    @BeforeEach
    fun setupKoin() {
        val overridesModule = module(createdAtStart = true, override = true,
            moduleOverrides)
        startKoin {
            printLogger()
            val environmentModules = loadEnvironmentModules(environment).toTypedArray()
            val overridesModule = module(createdAtStart = true, override = true,
                moduleOverrides)

            modules(
                *environmentModules,
                overridesModule
            )
        }
    }

    @AfterEach
    fun teardownKoin() {
        stopKoin()
    }


}


