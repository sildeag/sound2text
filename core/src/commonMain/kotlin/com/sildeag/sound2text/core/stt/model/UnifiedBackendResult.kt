package com.sildeag.sound2text.core.stt.model

/**
 * Lightweight data transfer object bridging raw backend data
 * to final/partial states inside the orchestrator.
 */
data class UnifiedBackendResult(
    val text: String,
    val isFinal: Boolean,
    val error: String? = null
) {
    val isError: Boolean get() = error != null
}
