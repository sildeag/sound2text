package com.sildeag.sound2text.uicommon.viewmodel

class FeatureListViewModel(
    private val loader: FeatureLoader
) : ViewModel() {
    private val _features =
        MutableStateFlow<List<FeatureDescriptor>>(emptyList())
    val features = _features.asStateFlow()
    fun load() {
        _features.value = loader.loadSupportedFeatures()
    }
}
