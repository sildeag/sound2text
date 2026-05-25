package com.sildeag.sound2text.uicommon.state

sealed interface SttAction {
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Start : SttAction
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Stop : SttAction
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Reset : SttAction
}
