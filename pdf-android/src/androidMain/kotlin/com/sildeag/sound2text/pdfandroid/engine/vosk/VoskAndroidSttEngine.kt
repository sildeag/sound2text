package com.sildeag.sound2text.pdfandroid.engine.vosk

import android.content.// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.stt.*
import org.vosk.Model

class VoskAndroidSttEngine(
    private val // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context,
    private val model: Model
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        return VoskAndroidSttService(// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, model, config)
    }
}
