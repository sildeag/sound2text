package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.service.recognizer.Recognizer
import com.sildeag.sound2text.ui.SoundScreen
import com.sildeag.sound2text.ui.SoundViewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module



val uiModule = module {

    factory { SoundScreen() }

    factory<SoundViewModel> { params ->
        val p = params.get<SoundViewModelParams>()

        val recognizerFactory: () -> Recognizer = getKoin().get()
        println("PulseLogic factory received params: ${p}")
        val pulseLogicParams = PulseLogicParams(
            onText = p.onText,
            onMic = p.onMic,
            onPulse = p.onPulse,
            onPulseUpdate = p.onPulseUpdate,
            onPulseColor = p.onPulseColor,
            recognizerFactory = recognizerFactory,
            environment = p.environment

        )

        val pulseLogic: PulseLogic = getKoin().get(
            parameters = { parametersOf(pulseLogicParams) })

        PulseLogic(
            soundPlayer = get(),
            recognizer = recognizerFactory(),
            noteEditor = get(),
            settings = get<AppSettings>(),
            logger = get(),
            noteProvider = get(),
            onText = p.onText,
            onMic = p.onMic,
            onPulse = p.onPulse,
            onPulseUpdate = p.onPulseUpdate,
            onPulseColor = p.onPulseColor,
            environment = p.environment
        )

        SoundViewModel(
            noteEditor = get(),
            pulseLogic = pulseLogic,
            settings = get<AppSettings>(),
            noteProvider = get(),
            onText = p.onText,
            onMic = p.onMic,
            onPulseColor = p.onPulseColor,
            onPulseUpdate = p.onPulseUpdate,
            onPulse = p.onPulse,
            environment = p.environment
        )
    }
}

