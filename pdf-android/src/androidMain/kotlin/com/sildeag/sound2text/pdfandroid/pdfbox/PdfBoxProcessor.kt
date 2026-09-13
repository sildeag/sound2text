package com.sildeag.sound2text.pdfandroid.pdfbox

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.pdf.model.PdfPage

import com.sildeag.sound2text.uicommon.models.UiPdfPage
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class PdfBoxProcessor : PdfProcessor {

    suspend fun loadPdfBytes(bytes: ByteArray): List<UiPdfPage> =
        withContext(Dispatchers.IO) {
            val temp = File.createTempFile("pdf", ".pdf")
            temp.writeBytes(bytes)

            val fd = ParcelFileDescriptor.open(
                temp,
                ParcelFileDescriptor.MODE_READ_ONLY
            )

            val renderer = PdfRenderer(fd)

            val pages = (0 until renderer.pageCount).map { index ->
                val page = renderer.openPage(index)

                val bitmap = Bitmap.createBitmap(
                    page.width,
                    page.height,
                    Bitmap.Config.ARGB_8888
                )

                page.render(
                    bitmap,
                    null,
                    null,
                    PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                )

                page.close()

                UiPdfPage(
                    index = index,
                    text = "",
                    bitmap = bitmap.asImageBitmap(),
                    width = bitmap.width,
                    height = bitmap.height,
                    pageNumber = index + 1
                )
            }

            renderer.close()
            fd.close()
            temp.delete()

            pages
        }

    override suspend fun loadPdf(bytes: PdfDocument): List<PdfPage> =
        withContext(Dispatchers.IO) {
            bytes.pages.map { page ->
                PdfPage(
                    index = page.index,
                    text = page.text
                )
            }
        }

}
