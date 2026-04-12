package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.Environment
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.service.logic.PulseLogicParams
import com.sildeag.sound2text.service.recognizer.Recognizer
import org.koin.dsl.module

val pulseModule = module {

    factory<PulseLogic> { (pulseParams: PulseLogicParams) ->
        PulseLogic(
            soundPlayer = get(),
            recognizer = pulseParams.recognizerFactory(),
            noteEditor = get(),
            settings = get<AppSettings>(),
            logger = get(),
            noteProvider = get(),
            onText = pulseParams.onText,
            onMic = pulseParams.onMic,
            onPulse = pulseParams.onPulse,
            onPulseUpdate = pulseParams.onPulseUpdate,
            onPulseColor = pulseParams.onPulseColor,
            environment = pulseParams.environment
        )
    }
}
