import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("internal.kmp.compose.library")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val composeMppVer = libs.findVersion("compose-mpp-ver").get().requiredVersion

// Collect feature projects to avoid duplicating the loop across source sets
val featureProjects = rootProject.subprojects.filter { it.name.startsWith("feature") }
// Diagnostic log during configuration to verify project discovery
project.logger.lifecycle("[${project.name}] Discovered ${featureProjects.size} feature projects: ${featureProjects.map { it.name }}}")

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.findLibrary("androidx-navigationevent").get())
            implementation(project(":ui-common"))
            implementation(project(":di"))
        }
        jvmMain.dependencies {
            implementation(project(":pdf-desktop"))
            implementation("org.jetbrains.compose.desktop:desktop:$composeMppVer")
            runtimeOnly("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:$composeMppVer")
            rootProject.subprojects
                .filter { it.name.startsWith("feature") }
                .forEach { featureProj ->
                    logger.lifecycle("Adding JVM runtime dependency: ${featureProj.path}")
                    runtimeOnly(project(featureProj.path))
                }

            //featureProjects.forEach { featureProj ->
            //    logger.lifecycle("Adding JVM runtime dependency: ${featureProj.path}")
            //    runtimeOnly(project(featureProj.path))
            //}

            androidMain.dependencies {
                implementation(project(":pdf-android"))
                featureProjects.forEach { featureProj ->
                    logger.lifecycle("Adding Android dependency: ${featureProj.path}")
                    implementation(project(featureProj.path))
                }
            }
        }

        targets.named<org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget>("jvm") {
            val mainCompilation = compilations.getByName("main")
            tasks.register<JavaExec>("run") {
                group = "application"
                description = "Runs the Compose Desktop application"
                mainClass.set("com.sildeag.sound2text.appcommon.MainKt")
                classpath = mainCompilation.runtimeDependencyFiles +
                        mainCompilation.output.allOutputs
                jvmArgs("--enable-native-access=ALL-UNNAMED")

                // Optional: Print runtime classpath during execution phase so you
                // can verify exactly what JARs/projects are loaded when you launch!
                doFirst {
                    println("--- Executing Main with Runtime Dependencies ---")
                    classpath.files.forEach { file ->
                        println("  -> ${file.name}")
                    }
                }
            }

        }
    }
}