package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.*

sealed class RecordingState {
    object Idle : RecordingState()
    object Starting : RecordingState()
    object Recording : RecordingState()
    object Stopping : RecordingState()
    data class Streaming(val partial: SttResult) : RecordingState()
    data class Finished(val final: SttResult?) : RecordingState()
}
