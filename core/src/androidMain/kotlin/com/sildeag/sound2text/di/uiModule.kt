package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.service.logic.PulseLogic
import com.sildeag.sound2text.core.service.logic.PulseLogicParams
import com.sildeag.sound2text.core.ui.SoundScreen
import com.sildeag.sound2text.core.ui.SoundViewModel
import com.sildeag.sound2text.core.ui.SoundViewModelParams
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
