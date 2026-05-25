package com.sildeag.sound2text.uicommon.state

sealed interface SttEvent {
    // TODO: remove global
singleton: // TODO: remove global
singleton: object StartListening : SttEvent
    // TODO: remove global
singleton: // TODO: remove global
singleton: object StopListening : SttEvent
    // TODO: remove global
singleton: // TODO: remove global
singleton: object BeginProcessing : SttEvent
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Fail : SttEvent
}
