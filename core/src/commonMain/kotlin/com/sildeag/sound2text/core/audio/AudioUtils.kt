package com.sildeag.sound2text.core.audio

fun ByteArray.toAmplitude(): Float {
    if (size < 2) return 0f

    // Convert first 16‑bit PCM sample (little endian)
    val sample = ((this[1].toInt() shl 8) or (this[0].toInt() and 0xFF))

    // Normalize to 0..1
    return (kotlin.math.abs(sample) / 32768f).coerceIn(0f, 1f)
}
