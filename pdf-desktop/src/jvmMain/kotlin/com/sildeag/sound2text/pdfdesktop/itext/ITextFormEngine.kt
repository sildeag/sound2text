package com.sildeag.sound2text.pdfdesktop.itext

import com.itextpdf.forms.PdfAcroForm
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import pdf.PdfFieldDescriptor
import pdf.PdfFormDescriptor
import pdf.PdfFormEngine

class ITextFormEngine(
    private val descriptor: PdfFormDescriptor
) : PdfFormEngine {
    private val pdf = PdfDocument(PdfReader(descriptor.path))
    private val form = PdfAcroForm.getAcroForm(pdf, true)
    override fun listFields(): List<PdfFieldDescriptor> =
        descriptor.fields
    override fun fillField(name: String, value: String) {
        val field = form.getField(name)
        field?.setValue(value)
    }
    override fun saveTo(path: String) {
        val out = PdfDocument(PdfWriter(path))
        pdf.copyPagesTo(1, pdf.numberOfPages, out)
        out.close()
        pdf.close()
    }
}
