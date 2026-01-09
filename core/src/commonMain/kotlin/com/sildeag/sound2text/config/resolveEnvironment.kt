package com.sildeag.sound2text.config

import com.sildeag.sound2text.platform.PlatformEnvironment

fun resolveEnvironment(
    configEnv: Environment,
    platform: PlatformEnvironment
): Environment =
    if (platform.isDebug) Environment.DEV else configEnv