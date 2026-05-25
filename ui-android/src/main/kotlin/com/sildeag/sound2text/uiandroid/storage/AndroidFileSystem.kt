package com.sildeag.sound2text.storage
class AndroidFileSystem(private val // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context) : FileSystem {
    override suspend fun read(path: String) =
        // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.openFileInput(path).bufferedReader().readText()
    override suspend fun write(path: String, data: String) =
        // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.openFileOutput(path, // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
}
