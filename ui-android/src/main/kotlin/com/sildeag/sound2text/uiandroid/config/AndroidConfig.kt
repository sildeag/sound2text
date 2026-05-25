class AndroidConfig : Config {
    override val environment = Environment.Android
    override val settings = AppSettings()
    override val flags = FeatureFlags()
}
