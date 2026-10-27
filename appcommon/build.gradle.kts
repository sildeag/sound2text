import org.gradle.api.artifacts.VersionCatalogsExtension


plugins {
    id("internal.kmp.compose.library")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val composeMppVer = libs.findVersion("compose-mpp").get().requiredVersion

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.findLibrary("androidx-navigationevent").get())
            implementation(project(":ui-common"))
            implementation(project(":di"))

            rootProject.subprojects.filter { it.name.startsWith("feature") }.forEach { featureProj ->
                runtimeOnly(project(featureProj.path))
            }
        }
        jvmMain.dependencies {
            implementation("org.jetbrains.compose.desktop:desktop:$composeMppVer")
            // 2. Add this line to explicitly force Gradle to package
            // the missing Windows native binary components into the jvmRun runtime environment
            // Explicit platform coordinate replacement for compose.desktop.currentOs
            runtimeOnly("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:$composeMppVer")
        }
    }

    targets.named<org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget>("jvm") {
        val mainCompilation = compilations.getByName("main")
        tasks.register<JavaExec>("run") {
            group = "application"
            description = "Runs the Compose Desktop application"
            mainClass.set("com.sildeag.sound2text.appcommon.MainKt")
            classpath = mainCompilation.runtimeDependencyFiles + mainCompilation.output.classesDirs
            jvmArgs("--enable-native-access=ALL-UNNAMED")
        }
    }
}

/*
plugins {
    kotlin("multiplatform")
    id("internal.kmp.compose.library")
}

val jfxVersion: String = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
    android {
        namespace = "com.sildeag.sound2text.appcommon"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":di"))
            // commonMain dependencies are in internal.kmp.base
        }
        commonTest.dependencies {

        }
        
        androidMain.dependencies {
            implementation(libs.androidx.compose.runtime)
            implementation(libs.vosk.android)
        }
        jvmMain.dependencies {
            // commonMain dependencies are in internal.kmp.base
        }
        jvmTest.dependencies {
            // commonMain dependencies are in internal.kmp.base
        }
    }
}
*/