# Preserve implementations of FeatureModuleProvider from R8 tree-shaking
-keepclassmembers class * implements com.sildeag.sound2text.di.FeatureModuleProvider {
    public <init>();
}
-keeppackagenames META-INF.services
