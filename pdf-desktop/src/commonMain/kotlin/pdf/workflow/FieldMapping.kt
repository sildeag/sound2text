package pdf.workflow

import com.sildeag.sound2text.core.stt.SttModelInfo

data class FieldMapping(
    val field: PdfFieldDescriptor,
    // STT engine selection (source of truth)
    val engineName: String = "vosk",
    // Language for transcription (source of truth)
    override val language: String = "en-US",
    // Desktop model info
    override val modelPath: String? = null,
    override val modelFile: String? = null,
    // Android model info
    override val androidModelDir: String? = null,
    override val androidModelFile: String? = null,
    // Optional pre-filled value
    val value: String? = null
) : SttModelInfo
