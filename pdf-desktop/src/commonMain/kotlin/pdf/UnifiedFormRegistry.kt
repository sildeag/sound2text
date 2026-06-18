package pdf

class UnifiedFormRegistry(
    private val plugins: List<PdfFormPlugin>
) {
    /**
     * Returns the names of all registered PDF form engines.
     */
    fun listEngines(): List<String> =
        plugins.map { it.engineName }
    /**
     * Returns all PDF forms discovered by all plugins.
     */
    fun listForms(basePath: String): List<PdfFormDescriptor> =
        plugins.flatMap { plugin ->
            plugin.discoverForms(basePath)
        }
    /**
     * Returns the plugin for a specific engine, or null if not
    found.
     */
    fun getPlugin(engineName: String): PdfFormPlugin? =
        plugins.firstOrNull { it.engineName == engineName }
}
