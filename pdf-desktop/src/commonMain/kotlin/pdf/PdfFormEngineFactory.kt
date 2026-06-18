package pdf

interface PdfFormEngineFactory {
    fun load(descriptor: PdfFormDescriptor): PdfFormEngine
}
