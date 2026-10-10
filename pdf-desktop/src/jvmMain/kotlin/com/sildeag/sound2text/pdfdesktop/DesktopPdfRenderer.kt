package com.sildeag.sound2text.pdfdesktop

import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfDocument
//import com.itextpdf.kernel.pdf.canvas.parser.PdfImageRenderer
import com.sildeag.sound2text.core.pdf.api.PdfRenderer
import com.sildeag.sound2text.core.pdf.model.PdfPage
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.ByteArrayOutputStream
class DesktopPdfRenderer : PdfRenderer {
    override suspend fun render(page: PdfPage, width: Int):
            ByteArray {
        // Placeholder: you will plug in your actual rendering pipeline
        val image = BufferedImage(page.width, page.height,
            BufferedImage.TYPE_INT_RGB)
        val out = ByteArrayOutputStream()
        ImageIO.write(image, "png", out)
        return out.toByteArray()
    }
}