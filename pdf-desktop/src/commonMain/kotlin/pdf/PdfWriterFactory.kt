package pdf

interface PdfWriterFactory {
    fun write(content: String, outputPath: String)
}
/*expect fun createPdfWriterFactory(): PdfWriterFactory*/

