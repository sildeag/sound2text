package com.sildeag.sound2text.core.usecase

class ProcessAudioUseCase(
    private val engine: AudioProcessor
) {
    suspend operator fun invoke(bytes: ByteArray): String {
        return engine.process(bytes)
    }
}