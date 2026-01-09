package com.sildeag.sound2text.di

val testPulseModule = module {
    factory { (params: PulseLogicParams) -> PulseLogic(params) }
}
