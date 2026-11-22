package com.sildeag.sound2text.pdfdesktop.pdf.render

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
/*
package com.sildeag.sound2text.pdfdesktop.pdf.render

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.render.PdfRenderer as CoreRenderer
import java.io.File
import android.os.ParcelFileDescriptor
import com.sildeag.sound2text.core.pdf.render.RenderedPage

class AndroidPdfRenderer(private val bytes: ByteArray) : CoreRenderer {
    override suspend fun render(pageIndex: Int): RenderedPage {
        val temp = File.createTempFile("pdf", ".pdf")
        temp.writeBytes(bytes)
        val fd = ParcelFileDescriptor.open(temp,
            ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fd)
        val page = renderer.openPage(pageIndex)
        val bitmap = Bitmap.createBitmap(
            page.width,
            page.height,
            Bitmap.Config.ARGB_8888
        )
        page.render(bitmap, null, null,
            PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        val compose = bitmap.asImageBitmap()
        renderer.close()
        fd.close()
        temp.delete()
        return RenderedPage(
            bitmap = compose,
            width = bitmap.width,
            height = bitmap.height
        )
    }
}

 */