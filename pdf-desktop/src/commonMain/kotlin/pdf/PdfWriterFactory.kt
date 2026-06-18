package pdf

interface PdfWriterFactory {
    fun write(content: String, outputPath: String)
}

