package com.sildeag.sound2text.uidesktop.di

val testPulseModule = module {
    factory { (params: PulseLogicParams) -> PulseLogic(params) }
}
