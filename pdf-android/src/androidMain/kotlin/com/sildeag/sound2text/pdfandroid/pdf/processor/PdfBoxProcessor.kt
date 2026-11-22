package com.sildeag.sound2text.pdfandroid.pdf.processor

import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.ByteArrayInputStream

class PdfBoxProcessor : PdfProcessor {
    override suspend fun loadPdf(bytes: ByteArray): List<PdfPage> {
        return ByteArrayInputStream(bytes).use { inputStream ->
            PDDocument.load(inputStream).use { document ->
                val stripper = PDFTextStripper()
                (0 until document.numberOfPages).map { index ->
                    stripper.startPage = index + 1
                    stripper.endPage = index + 1
                    val pageText = stripper.getText(document).trim()

                    PdfPage(
                        index = index,
                        pageNumber = index + 1,
                        text = pageText
                    )
                }
            }
        }
    }
}


/*
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.pdfandroid.AndroidLoadedUiPdf
import com.sildeag.sound2text.pdfandroid.pdf.render.AndroidPdfRenderer
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage // Now imported from :ui-common
import com.sildeag.sound2text.uicommon.pdf.mappers.mapToUi  // Now imported from :ui-common
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class PdfBoxProcessor (
    private val uiPdfProcessor: UiPdfProcessor,
    private val renderer: AndroidPdfRenderer,
) {

    suspend fun loadUiPdf(bytes: ByteArray): AndroidLoadedUiPdf {
        // 1. Delegate page parsing and rendering to UiPdfProcessor
        val uiPages: MutableList<UiPdfPage> = uiPdfProcessor.loadUiPdf(bytes)
    //suspend fun loadPdf(bytes: ByteArray): List<UiPdfPage> =
    //    withContext(Dispatchers.IO) {
    //    val temp = File.createTempFile("pdf_render", ".pdf")
    //    temp.writeBytes(bytes)

    //  val fd = ParcelFileDescriptor.open(
    //        temp,
    //        ParcelFileDescriptor.MODE_READ_ONLY
    //    )

        val renderer = PdfRenderer(fd)
        val pages = (0 until renderer.pageCount).map { index ->
        val page = renderer.openPage(index)


            val imageBitmap = bitmap.asImageBitmap()

            // Constructing core domain representation temporarily to pass to the mapper
//            val corePage = UiPdfPage(
//                index = index,
//                text = "" // Core text extraction result if available
//            )

            // Map using the shared mapper from :ui-common
//            mapToUi(
//                core = corePage,
//                bitmap = imageBitmap,
//                width = bitmap.width,
//                height = bitmap.height
//            )
//        }

            renderer.close()
            fd.close()
            temp.delete()

            pages
        }
}

 */
/*
package com.sildeag.sound2text.pdfandroid

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import java.io.ByteArrayInputStream

class AndroidUiPdfProcessor(
    private val uiPdfProcessor: UiPdfProcessor,
    private val metadataExtractor: AndroidPdfMetadataExtractor
) {

    suspend fun loadUiPdf(bytes: ByteArray): AndroidLoadedUiPdf {
        // 1. Delegate page parsing and rendering to UiPdfProcessor
        val uiPages: MutableList<UiPdfPage> = uiPdfProcessor.loadUiPdf(bytes)

        // 2. Extract Desktop-specific metadata with proper iText resource management
        val metadata = extractMetadataSafely(bytes)

        return AndroidLoadedUiPdf(
            pages = uiPages,
            metadata = metadata
        )
    }

    private fun extractMetadataSafely(bytes: ByteArray): PdfMetadata {
        val reader = PdfReader(ByteArrayInputStream(bytes))
        val pdf = PdfDocument(reader)
        return try {
            metadataExtractor.extract(pdf)
        } finally {
            // Guarantee resource cleanup regardless of extraction failures
            pdf.close()
            reader.close()
        }
    }
}

 */
/*
package com.sildeag.sound2text.pdfandroid.pdfbox

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
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
                    pageNumber = index + 1,
                    bitmap = bitmap.asImageBitmap(),
                    width = bitmap.width,
                    height = bitmap.height,
                )
            }

            renderer.close()
            fd.close()
            temp.delete()

            pages
        }

    override suspend fun loadPdf(bytes: ByteArray): List<UiPdfPage> =
        withContext(Dispatchers.IO) {
            bytes.pages.map { page ->
                UiPdfPage(
                    index = index,
                    pageNumber = index + 1,
                    bitmap = page.bitmap.asImageBitmap(),
                    width = bitmap.width,
                    height = bitmap.height,
                )

            }
        }

}
*/