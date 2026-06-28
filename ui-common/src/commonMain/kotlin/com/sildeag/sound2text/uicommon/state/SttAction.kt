package com.sildeag.sound2text.uicommon.state

sealed interface SttAction {
    object Start : SttAction
    object Stop : SttAction
    object Reset : SttAction
}
