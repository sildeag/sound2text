package com.sildeag.sound2text.android.di

import com.sildeag.sound2text.android.permissions.PermissionManager
import org.koin.dsl.module

val androidPermissionsModule = module {
    single { PermissionManager(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = get()) }
}

