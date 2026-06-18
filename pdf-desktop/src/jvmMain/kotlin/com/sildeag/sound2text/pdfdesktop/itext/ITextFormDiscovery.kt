package com.sildeag.sound2text.pdfdesktop.itext

import com.itextpdf.forms.PdfAcroForm
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.*
import pdf.FieldType
import pdf.PdfFieldDescriptor
import pdf.PdfFormDescriptor
import pdf.PdfFormDiscovery
import java.io.File

//private val PdfAcroForm.fields: Any

class ITextFormDiscovery : PdfFormDiscovery {
    override fun discoverForms(basePath: String): List<PdfFormDescriptor> {
        val root = File(basePath)
        if (!root.exists()) return emptyList()
        return root.walkTopDown()
            .filter { it.isFile && it.extension.lowercase() == "pdf" }
            .mapNotNull { extractDescriptor(it) }
            .toList()
    }
    private fun extractDescriptor(file: File): PdfFormDescriptor? {
        return try {
            val pdf = PdfDocument(PdfReader(file))
            val form = PdfAcroForm.getAcroForm(pdf, false) ?: return null
            val fields = form.fields.map { (name, field) ->
                val rawType = field.formType?.type?.toString()
                PdfFieldDescriptor(
                    name = name,
                    type1 = "text",
                    type = mapFieldType(rawType),
                    voiceEnabled = rawType == "Tx"
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
