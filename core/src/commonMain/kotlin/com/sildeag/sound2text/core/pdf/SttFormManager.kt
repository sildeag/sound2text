package com.sildeag.sound2text.core.pdf

class SttFormManager(
    private val scanner: PdfFormScanner,
    private val loader: PdfResourceLoader,
    private val processor: PdfProcessor
) {
    suspend fun discoverForms(basePath: String): Map<String,
            List<PdfForm>> {
        val forms = scanner.scan(basePath)
        return groupMultiPartForms(forms)
    }
    private fun groupMultiPartForms(forms: List<PdfForm>):
            Map<String, List<PdfForm>> =
        forms.groupBy { form ->
            form.id.substringBefore(".part")
        }
    suspend fun loadForm(groupId: String, grouped: Map<String,
            List<PdfForm>>): List<PdfPage> {
        val parts = grouped[groupId] ?: return emptyList()
        val sortedParts = parts.sortedBy { part ->
            part.id.substringAfter(".part", "0").toIntOrNull() ?: 0
        }
        val allPages = mutableListOf<PdfPage>()
        for (part in sortedParts) {
            val bytes = loader.load(part.path)
            val pages = processor.loadPdf(bytes)
            allPages += pages
        }
        return allPages
    }
}