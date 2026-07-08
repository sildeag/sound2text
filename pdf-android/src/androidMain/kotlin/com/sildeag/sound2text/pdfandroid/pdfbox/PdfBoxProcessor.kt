package com.sildeag.sound2text.pdfandroid.pdfbox

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.PdfProcessor
import com.sildeag.sound2text.core.pdf.PdfPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class PdfBoxProcessor : PdfProcessor {
    override suspend fun loadPdf(bytes: ByteArray): List<PdfPage> =
        withContext(Dispatchers.IO) {
            val temp = File.createTempFile("pdf", ".pdf")
            temp.writeBytes(bytes)
            val fd = ParcelFileDescriptor.open(temp,
                ParcelFileDescriptor.MODE_READ_ONLY)
            val renderer = PdfRenderer(fd)
            val pages = (0 until renderer.pageCount).map { index ->
                val page = renderer.openPage(index)
                val bitmap = Bitmap.createBitmap(
                    page.width,
                    page.height,
                    Bitmap.Config.ARGB_8888
                )
                page.render(bitmap, null, null,
                    PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                page.close()
                PdfPage(
                    pageNumber = index + 1,
                    bitmap = bitmap.asImageBitmap(),
                    index = TODO(),
                    text = TODO()
                )
            }
            renderer.close()
            fd.close()
            temp.delete()
            pages
        }
}