package com.sildeag.sound2text.uicommon.viewmodel

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttStreamingController

class SttViewModelFactory(
    private val controllerFactory: (SttConfig) -> SttStreamingController
) {
    fun create(config: SttConfig): SttViewModel {
        return SttViewModel(controllerFactory, config)
    }
}