package com.sildeag.sound2text.coreui.sound

interface SoundEngine {
    suspend fun startRecording(
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit
    )
    suspend fun stopRecording()
}
