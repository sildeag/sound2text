package com.sildeag.sound2text.di

import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.ui.SoundScreen
import com.sildeag.sound2text.ui.SoundViewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val uiModule = module {
    factory { SoundScreen() }

    factory { (params: SoundViewModelParams) ->
        val pulseLogic: PulseLogic = get(parameters = { parametersOf(params.toPulseLogicParams()) })
        SoundViewModel(
            noteEditor = get(),
            pulseLogic = pulseLogic,
            settings = get<AppSettings>(),
            noteProvider = get(),
            onText = params.onText,
            onMic = params.onMic,
            onPulseColor = params.onPulseColor,
            onPulseUpdate = params.onPulseUpdate,
            onPulse = params.onPulse,
            environment = params.environment
        )
    }
}

private fun SoundViewModelParams.toPulseLogicParams(): PulseLogicParams {
    return PulseLogicParams(
        onText = onText,
        onMic = onMic,
        onPulse = onPulse,
        onPulseUpdate = onPulseUpdate,
        onPulseColor = onPulseColor,
        recognizerFactory = get(),
        environment = environment
    )
}
