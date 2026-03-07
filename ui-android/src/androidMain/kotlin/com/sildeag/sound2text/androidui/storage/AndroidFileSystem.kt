package com.sildeag.sound2text.storage
class AndroidFileSystem(private val context: Context) : FileSystem {
    override suspend fun read(path: String) =
        context.openFileInput(path).bufferedReader().readText()
    override suspend fun write(path: String, data: String) =
        context.openFileOutput(path, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
}