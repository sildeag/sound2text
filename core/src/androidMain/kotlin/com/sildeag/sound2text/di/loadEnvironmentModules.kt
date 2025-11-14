package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.core.config.Environment
import org.koin.core.module.Module

fun loadEnvironmentModules(
    override: Environment? = null
): List<Module> {
    val env = override ?: AppEnvironment.current
    return when (env) {
        Environment.DEV -> listOf(
            //configModule,
            audioModule,
            loggingModule,
            resourceModule,
            noteModuleDev,
            noteEditorModule,
            pulseModule,
            recognizerModule,
            pdfWriterModule,
            uiModule
        )

        Environment.PROD -> listOf(
            appSettingsModule,
            audioModule,
            loggingModule,
            resourceModule,
            noteModuleProd,
            noteEditorModule,
            pulseModule,
            recognizerModule,
            pdfWriterModule,
            uiModule
        )

        Environment.TEST -> listOf(
            appSettingsModule,
            audioModule,
            loggingModule,
            resourceModule,
            noteModuleProd,
            noteEditorModule,
            pulseModule,
            recognizerModule,
            pdfWriterModule,
            uiModule
        )
    }
}
