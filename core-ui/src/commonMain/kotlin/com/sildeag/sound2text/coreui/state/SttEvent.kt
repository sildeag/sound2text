package com.sildeag.sound2text.coreui.state

sealed interface SttEvent {
    object StartListening : SttEvent
    object StopListening : SttEvent
    object BeginProcessing : SttEvent
    object Fail : SttEvent
}