package com.sildeag.sound2text.config

import android.media.AudioRecord
import android.media.MediaRecorder

data class AndroidAudioDefaults(
    val audioSource: Int = MediaRecorder.AudioSource.MIC,
    val performanceMode: Int =
        AudioRecord.PERFORMANCE_MODE_LOW_LATENCY
)