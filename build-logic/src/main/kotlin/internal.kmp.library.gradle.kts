import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("internal.kmp.base")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.dokka")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
    android {
        namespace = "com.sildeag.sound2text." + project.name.replace("-", "")
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
}

afterEvaluate {
    extensions.findByType<org.jetbrains.dokka.gradle.DokkaExtension>()?.apply {
        dokkaSourceSets.configureEach {
            includes.from(project.layout.projectDirectory.file("DEVLOG.md"))
        }
    }
}
