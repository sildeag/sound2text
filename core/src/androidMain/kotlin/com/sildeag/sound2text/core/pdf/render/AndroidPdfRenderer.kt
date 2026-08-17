package com.sildeag.sound2text.core.pdf.render

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.core.pdf.render.PdfRenderer as CoreRenderer
import com.sildeag.sound2text.core.pdf.render.RenderedPage
import java.io.File
import android.os.ParcelFileDescriptor

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
