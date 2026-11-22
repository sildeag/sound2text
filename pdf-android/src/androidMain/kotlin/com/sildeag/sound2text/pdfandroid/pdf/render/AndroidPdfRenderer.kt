package com.sildeag.sound2text.pdfandroid.pdf.render

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
/*
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
 */