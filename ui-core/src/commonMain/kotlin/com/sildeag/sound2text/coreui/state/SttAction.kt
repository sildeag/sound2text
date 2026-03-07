package com.sildeag.sound2text.coreui.state

sealed interface SttAction {
    data object Start : SttAction
    data object Stop : SttAction
    data object Reset : SttAction
}