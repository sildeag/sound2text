package com.sildeag.sound2text.core.pdf.render.pdfbox

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.core.pdf.render.RenderedPage
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.ByteArrayInputStream
class PdfBoxRenderer(
    private val bytes: ByteArray
) : PdfRenderer {
    override suspend fun render(pageIndex: Int): RenderedPage {
        val doc = PDDocument.load(ByteArrayInputStream(bytes))
        val renderer = PDFRenderer(doc)
        val awtImage = renderer.renderImageWithDPI(pageIndex, 150f)
        val composeImage = awtImage.toComposeImageBitmap()
        val width = awtImage.width
        val height = awtImage.height
        doc.close()
        return RenderedPage(
            bitmap = composeImage,
            width = width,
            height = height
        )
    }
}
