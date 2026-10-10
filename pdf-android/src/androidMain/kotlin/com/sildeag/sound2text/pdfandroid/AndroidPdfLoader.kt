package com.sildeag.sound2text.pdfandroid

import android.graphics.pdf.PdfRenderer
import android.os.Build
import android.os.ParcelFileDescriptor
import androidx.annotation.RequiresApi
import com.sildeag.sound2text.core.pdf.api.LoadedPdf
import com.sildeag.sound2text.core.pdf.api.PdfLoader
import com.sildeag.sound2text.core.pdf.model.PdfPage

class AndroidPdfLoader(
    private val metadataExtractor:
    AndroidPdfMetadataExtractor
) : PdfLoader {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(bytes: ByteArray): LoadedPdf
    {
        val temp = kotlin.io.path.createTempFile().toFile()
        temp.writeBytes(bytes)
        val fd = ParcelFileDescriptor.open(temp,
            ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fd)
        val pages = (0 until renderer.pageCount).map { index ->
            val page = renderer.openPage(index)
            val pdfPage = PdfPage(
                index = index,
                width = page.width,
                height = page.height
            )
            page.close()
            pdfPage
        }
        val metadata = metadataExtractor.extract(bytes)
        return LoadedPdf(pages, metadata)
    }
}
