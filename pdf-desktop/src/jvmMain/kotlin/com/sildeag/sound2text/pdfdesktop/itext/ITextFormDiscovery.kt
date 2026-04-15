package com.sildeag.sound2text.pdfdesktop.itext

import com.itextpdf.forms.PdfAcroForm
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.*
import java.io.File
class ITextFormDiscovery : PdfFormDiscovery {
    override fun discoverForms(basePath: String):
            List<PdfFormDescriptor> {
        val root = File(basePath)
        if (!root.exists()) return emptyList()
        return root.walkTopDown()
            .filter { it.isFile && it.extension.lowercase() ==
                    "pdf" }
            .mapNotNull { extractDescriptor(it) }
            .toList()
    }
    private fun extractDescriptor(file: File): PdfFormDescriptor? {
        return try {
            val pdf = PdfDocument(PdfReader(file))
            val form = PdfAcroForm.getAcroForm(pdf, false) ?: return
            null
            val fields = form.formFields.map { (name, field) ->
                PdfFieldDescriptor(
                    name = name,
                    type = mapFieldType(field.formType),
                    voiceEnabled = field.formType == "Tx", // text
                    fields only
                            language = null
                )
            }
            pdf.close()
            PdfFormDescriptor(
                engine = "itext",
                formName = file.nameWithoutExtension,
                path = file.absolutePath,
                fields = fields
            )
        } catch (e: Exception) {
            null
        }
    }
    private fun mapFieldType(type: String?): FieldType =
        when (type) {
            "Tx" -> FieldType.TEXT
            "Btn" -> FieldType.CHECKBOX
            "Ch" -> FieldType.DROPDOWN
            else -> FieldType.UNKNOWN
        }
}