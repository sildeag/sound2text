class PdfViewModel(
    private val loader: PdfLoader,
    private val renderer: PdfRenderer,
    private val mapper: DefaultPdfUiMapper,
    private val extractor: PdfTextExtractor? = null
) {
    private val _state = MutableStateFlow(PdfState())
    val state: StateFlow<PdfState> = _state
    suspend fun load(path: String) {
        _state.update { it.copy(isLoading = true) }
        val coreDoc = loader.load(path)
        val uiDoc = UiPdfDocument(
            name = coreDoc.name,
            pageCount = coreDoc.pageCount,
            pages = coreDoc.pages.map { UiPdfPageSummary(it.index) },
            metadata = coreDoc.metadata
        )
        _state.update { it.copy(document = uiDoc, isLoading =
            false) }
    }
    suspend fun renderPage(index: Int) {
        val rendered = renderer.render(index)
        val corePage = loader.getPage(index)
        val uiPage = mapper.map(
            corePage,
            rendered.bitmap,
            rendered.width,
            rendered.height
        )
        _state.update { it.copy(currentPage = uiPage) }
    }
    suspend fun extractText(bytes: ByteArray) {
        extractor ?: return
        val text = extractor.extract(bytes)
        _state.update { it.copy(extractedText = text) }
    }
}

