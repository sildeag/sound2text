package com.sildeag.sound2text.uilegacy.di

val testPulseModule = module {
    factory { (params: PulseLogicParams) -> PulseLogic(params) }
}
