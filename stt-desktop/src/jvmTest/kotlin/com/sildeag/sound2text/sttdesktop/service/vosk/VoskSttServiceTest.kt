package com.sildeag.sound2text.sttdesktop.service.vosk

package com.sildeag.sound2text.sttdesktop.service.vosk
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.vosk.Model
import org.vosk.Recognizer
class VoskSttServiceTest {
    @Test
    fun `returns Success when recognizer produces text`() {
        val model = FakeModel()
        val config = SttConfig()
        val service = VoskSttService(model, config)
        val audio = ByteArray(320) // dummy PCM bytes
        val result = service.transcribe(audio)
        assertTrue(result is SttResult.Success)
        val success = result as SttResult.Success
        assertEquals("hello world", success.data.text)
    }
    // Very lightweight fakes – you can refine as needed
    // SttConfig(modelPath = "fake", sampleRate = 8000f)
    private class FakeModel : Model("fake-path")
    private class FakeRecognizer(model: Model, sampleRate: Float) :
        Recognizer(model, sampleRate) {
        override fun finalResult(): String {
            return """{"text":"hello world"}"""
        }
    }
}
