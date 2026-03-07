package com.sildeag.sound2text.coreui.viewmodel

import com.sildeag.sound2text.coreui.state.SttAction

fun dispatch(action: SttAction) {
    when (action) {
        SttAction.Start -> startRecording()
        SttAction.Stop -> stopRecording()
        SttAction.Reset -> reset()
    }
}

fun reset() {
    TODO("Not yet implemented")
}

fun stopRecording() {
    TODO("Not yet implemented")
}

fun startRecording() {
    TODO("Not yet implemented")
}
