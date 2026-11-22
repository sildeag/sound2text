package com.sildeag.sound2text.di

interface FeatureRegistry {
    val processors: List<FileProcessorFeature>
    fun findByRoute(route: String): FileProcessorFeature? {
        return processors.find { it.route == route || it.id == route }
    }
}
class FeatureRegistryImpl(
    private val _processors: List<FileProcessorFeature>
) : FeatureRegistry {
    override val processors: List<FileProcessorFeature> get() = _processors
}

/*
package com.sildeag.wac2wav.di

interface FeatureRegistry {
    val processors: List<FileProcessorFeature>
}

class FeatureRegistryImpl(
    private val _processors: List<FileProcessorFeature>
) : FeatureRegistry {
    override val processors: List<FileProcessorFeature> get() = _processors
}
 */