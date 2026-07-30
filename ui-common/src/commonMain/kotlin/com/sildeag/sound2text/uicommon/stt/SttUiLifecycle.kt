package com.sildeag.sound2text.uicommon.stt

sealed interface SttUiLifecycle {
    object Idle : SttUiLifecycle
    object Listening : SttUiLifecycle
    object Processing : SttUiLifecycle
    data class Error(val message: String) : SttUiLifecycle
}
