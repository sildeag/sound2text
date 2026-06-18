package pdf

interface PdfFormPlugin {
    val engineName: String
    fun createFactory(): PdfFormEngineFactory
    fun discoverForms(basePath: String): List<PdfFormDescriptor>
}
