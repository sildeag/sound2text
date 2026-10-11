package com.sildeag.sound2text.sttandroid.stt.whisper

import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
class WhisperSttEngine(
    private val whisper: WhisperBackend,
    private val onPartial: (String) -> Unit,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : BaseSttEngine() {
    override suspend fun start() {
        try {
            whisper.start()
        } catch (e: Exception) {
            onError("Whisper start error: ${e.message}")
        }
    }
    override suspend fun processAudio(chunk: ByteArray): SttResult {
        return try {
            val result = whisper.process(chunk)
            if (result.isFinal) {
                //onFinal(result.text)
                SttResult.Final(result.text)
            } else {
                //onPartial(result.text)
                SttResult.Partial(result.text)
            }
        } catch (e: Exception) {
            val msg = "Whisper audio error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun finish(): SttResult {
        return try {
            whisper.finish()
        } catch (e: Exception) {
            val msg = "Whisper finish error: ${e.message}"
            SttResult.Error(msg)
        }
    }
    /*
    override suspend fun finish(): SttResult {
        return try {
            val text = whisper.finish()
            //onFinal(text)
            SttResult.Final(text)
        } catch (e: Exception) {
            val msg = "Whisper finish error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }

     */
    override suspend fun stop() {
        whisper.stop()
    }
}
/*
import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
class WhisperSttEngine(
    private val whisper: WhisperBackend,
    private val onPartial: (String) -> Unit,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : BaseSttEngine() {
    override suspend fun start() {
        try {
            whisper.startSession()
        } catch (e: Exception) {
            onError("Whisper start error: ${e.message}")
        }
    }
    override suspend fun processAudio(chunk: ByteArray): SttResult {
        return try {
            val result = whisper.processSamples(chunk)
            if (result.isFinal) {
                onFinal(result.text)
                SttResult.Final(result.text)
            } else {
                onPartial(result.text)
                SttResult.Partial(result.text)
            }
        } catch (e: Exception) {
            val msg = "Whisper audio error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun finish(): SttResult {
        return try {
            val text = whisper.finalizeSession()
            onFinal(text)
            SttResult.Final(text)
        } catch (e: Exception) {
            val msg = "Whisper finish error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun stop() {
        whisper.stopSession()
    }
}

 */