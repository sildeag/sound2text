package com.sildeag.sound2text.uicommon.pdf.mappers

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage

interface PdfUiMapper<T> {
    fun map(core: PdfPage, bitmap: ImageBitmap, width: Int, height: Int): T
}

/**
 * Maps a single core domain [PdfPage] into a [UiPdfPage].
 */
fun mapToUi(
    core: PdfPage,
    bitmap: ImageBitmap,
    width: Int,
    height: Int
): UiPdfPage =
    UiPdfPage(
        index = core.index,
        pageNumber = core.index + 1,
        text = core.text,
        bitmap = bitmap,
        width = width,
        height = height
    )

/**
 * Extension function to map a collection of domain [PdfPage]s into a list of [UiPdfPage]s.
 */
fun Iterable<PdfPage>.toUiPages(
    bitmap: ImageBitmap,
    width: Int,
    height: Int
): List<UiPdfPage> = map { corePage ->
    mapToUi(
        core = corePage,
        bitmap = bitmap,
        width = width,
        height = height
    )
}

/**
 * Extension function to map a collection of domain [PdfPage]s into a [MutableList<UiPdfPage>].
 */
fun Iterable<PdfPage>.toMutableUiPages(
    bitmap: ImageBitmap,
    width: Int,
    height: Int
): MutableList<UiPdfPage> = toUiPages(bitmap, width, height).toMutableList()

/*
val domainPages: List<PdfPage> = coreProcessor.loadPdf(bytes)

// Returns List<UiPdfPage>
val uiPagesList: List<UiPdfPage> = domainPages.toUiPages(
    bitmap = renderedBitmap.asImageBitmap(),
    width = renderedBitmap.width,
    height = renderedBitmap.height
)

// Returns MutableList<UiPdfPage>
val uiPagesMutableList: MutableList<UiPdfPage> = domainPages.toMutableUiPages(
    bitmap = renderedBitmap.asImageBitmap(),
    width = renderedBitmap.width,
    height = renderedBitmap.height
)
 */