package com.sildeag.sound2text.di

import com.sildeag.sound2text.pdf.*
data class EnvUiCase(val config: Config, val expectedEnv:
Environment, val expectedUi: UiMode)
val allCases = listOf(
    EnvUiCase(fakeConfigDevCompose, Environment.DEV,
        UiMode.DesktopCompose),
    EnvUiCase(fakeConfigDevFxml, Environment.DEV,
        UiMode.DesktopFxml),
    EnvUiCase(fakeConfigProdCompose, Environment.PROD,
        UiMode.DesktopCompose),
    EnvUiCase(fakeConfigProdFxml, Environment.PROD,

    EnvUiCase(fakeConfigTestCompose, Environment.TEST,
        UiMode.DesktopCompose),
    EnvUiCase(fakeConfigTestFxml, Environment.TEST,
        UiMode.DesktopFxml)
)