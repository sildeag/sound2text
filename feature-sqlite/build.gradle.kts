plugins {
 id("internal.kmp.compose.library")
 //id("internal.kmp.room")
}

val jfxVersion:String = libs.versions.javafx.ver.get() // Gets "21.0.2" from TOML

kotlin {
 android {
     namespace = "com.sildeag.sound2text.feature.sqlite"
 }

 sourceSets {
  commonMain.dependencies {
     implementation(project(":core"))
     implementation(project(":ui-common"))
     implementation(project(":di"))
     implementation(libs.androidx.sqlite.bundled) // Bundled core SQLite
  }

  androidMain.dependencies {
     implementation(libs.androidx.sqlite.framework)
  }
  jvmMain.dependencies {
     implementation(libs.sqlite.jdbc)
  }
 }
}

/*
plugins {
 id("internal.kmp.library")
}
kotlin {
 sourceSets {
 commonMain {
 dependencies {
 implementation(project(":core"))
 implementation(project(":ui"))
 implementation(libs.koin.core)
 implementation(libs.compose.runtime)
 }
 }
 androidMain { }
 jvmMain { }
 }
}
