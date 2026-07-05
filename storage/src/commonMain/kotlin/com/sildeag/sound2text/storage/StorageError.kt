package com.sildeag.sound2text.storage

sealed class StorageError {
    object NotFound : StorageError()
    object PermissionDenied : StorageError()
    data class IOError(val message: String) : StorageError()
}
