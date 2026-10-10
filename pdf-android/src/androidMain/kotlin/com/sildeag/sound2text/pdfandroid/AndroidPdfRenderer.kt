package com.sildeag.sound2text.pdfandroid

import android.graphics.Bitmap
import android.os.Build
import android.graphics.pdf.PdfRenderer as AndroidPdfRendererApi
import com.sildeag.sound2text.core.pdf.api.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.annotation.RequiresApi
import com.sildeag.sound2text.core.pdf.model.PdfPage

import java.io.ByteArrayOutputStream
class AndroidPdfRenderer : PdfRenderer {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun render(page: PdfPage, width: Int): ByteArray {
        val temp = kotlin.io.path.createTempFile().toFile()
        val fd = ParcelFileDescriptor.open(temp,
            ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = AndroidPdfRendererApi(fd)
        val pdfPage = renderer.openPage(page.index)
        val bitmap = Bitmap.createBitmap(page.width,
            page.height, Bitmap.Config.ARGB_8888)
        pdfPage.render(bitmap, null, null,
            AndroidPdfRendererApi.Page.RENDER_MODE_FOR_DISPLAY)
        pdfPage.close()
        val out = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,
            out)
        return out.toByteArray()
    }
}
