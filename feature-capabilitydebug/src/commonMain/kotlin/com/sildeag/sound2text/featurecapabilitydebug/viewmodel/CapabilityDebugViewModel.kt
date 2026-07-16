package feature.capabilitydebug.viewmodel
import core.repository.TranscriptionRepository
import core.repository.PdfRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJobpackage feature.capabilitydebug.viewmodel
import core.repository.TranscriptionRepository
import core.repository.PdfRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import core.dispatcher.DispatcherProvider
class CapabilityDebugViewModel(
    private val transcriptionRepository: TranscriptionRepository,
    private val pdfRepository: PdfRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.io)
    private val _debugInfo = MutableStateFlow("Idle")
    val debugInfo: StateFlow<String> = _debugInfo
    fun testTranscription(bytes: ByteArray) {
        scope.launch {
            val text = transcriptionRepository.transcribe(bytes)
            _debugInfo.value = "Transcription: $text"
        }
    }
    fun testPdf(path: String) {
        scope.launch {
            val doc = pdfRepository.loadPdf(path)
            _debugInfo.value = "PDF Loaded: ${doc.pages.size} pages"
        }
    }
}