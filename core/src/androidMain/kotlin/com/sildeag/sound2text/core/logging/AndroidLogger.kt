package com.sildeag.sound2text.core.logging

import android.util.Log
import com.sildeag.sound2text.core.common.logging.Logger

class AndroidLogger : Logger {
    private val tag = "Sound2Text"
    override fun debug(message: String) =
        Log.d(tag, message)
    override fun info(message: String) =
        Log.i(tag, message)
    override fun warning(message: String) =
        Log.w(tag, message)
    override fun error(message: String, throwable: Throwable?) =
        Log.e(tag, message, throwable)
    override fun severe(message: String, throwable: Throwable?) =
        Log.wtf(tag, message, throwable)
}