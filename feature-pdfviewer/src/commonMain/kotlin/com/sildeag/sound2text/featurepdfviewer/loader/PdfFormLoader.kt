package com.sildeag.sound2text.featurepdfviewer.loader

import com.sildeag.sound2text.core.pdf.forms.PdfFormDescriptor

interface PdfFormLoader {
    suspend fun discoverForms(): List<PdfFormDescriptor>
}
