package com.sildeag.sound2text.featurerecording.viewmodel

fun reducer(state: SttState, action: Action): SttState = when
                                                                 (action) {
    is StartRecording -> state.copy(recordingState = Recording)
    is StopRecording -> state.copy(recordingState = Processing)
    is OnPartial -> state.copy(partialText = action.text)
    is OnFinal -> state.copy(finalText = action.text, recordingState
    = Idle)
    is OnError -> state.copy(recordingState = Error(action.message))
}
