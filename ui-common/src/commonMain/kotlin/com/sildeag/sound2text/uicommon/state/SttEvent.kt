package com.sildeag.sound2text.uicommon.state

sealed interface SttEvent {
    object StartListening : SttEvent
    object StopListening : SttEvent
    object BeginProcessing : SttEvent
    object Fail : SttEvent
}
