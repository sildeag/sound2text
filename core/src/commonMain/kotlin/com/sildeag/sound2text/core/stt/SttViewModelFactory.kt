package com.sildeag.sound2text.core.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttController
class SttViewModelFactory(
    private val controllerFactory: (SttConfig) -> SttController
) {
    fun create(config: SttConfig): SttViewModel {
        return SttViewModel(controllerFactory, config)
    }
}