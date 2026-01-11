class AndroidSttService(
    private val engine: SttEngine,
    private val config: SttConfig
) : SttService {
    override fun transcribe(audio: ByteArray): SttResult {
        val stt = engine.loadModel(config)
        return stt.transcribe(audio)
    }
}
