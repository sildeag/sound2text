package com.sildeag.sound2text.di.core.stt

package com.sildeag.sound2text.di
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskModelFactory
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidSttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import org.koin.core.module.Module
import org.koin.dsl.module
// Desktop module (wired in desktop app)
fun desktopSttModule(modelPath: java.nio.file.Path): Module = module {
    single { SttConfig() } // adjust fields as needed
    single {
        VoskModelFactory.loadModel(modelPath)
    }
    single<SttService> {
        VoskSttService(
            model = get(),
            config = get()
        )
    }
}
// Android module (wired in Android app)
fun androidSttModule(context: android.content.Context): Module = module {
    single { SttConfig() }
    single {
        VoskAndroidModelFactory.loadModel(context)
    }
    single<SttService> {
        VoskAndroidSttService(
            model = get(),
            config = get()
        )
    }
}
