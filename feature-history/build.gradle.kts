plugins {
 id("internal.kmp.library")
 //id("internal.kmp.room")
}

val jfxVersion = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
 android {
  namespace = "com.sildeag.sound2text.feature.history"
 }

 sourceSets {
  commonMain.dependencies {
   implementation(project(":core"))
   implementation(project(":ui-common"))
   implementation(libs.koin.core)
   implementation(libs.compose.mpp.runtime)
  }

  androidMain {
  }
  jvmMain {
  }
 }
}