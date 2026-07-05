package com.sildeag.sound2text.storage

sealed class StorageResult<out T> {
    data class Success<T>(val value: T) : StorageResult<T>()
    data class Failure(val error: StorageError) : StorageResult<Nothing>()
}