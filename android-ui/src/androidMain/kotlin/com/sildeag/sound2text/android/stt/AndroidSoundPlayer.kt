package com.sildeag.sound2text.stt

import android.media.MediaPlayer
import com.sildeag.sound2text.audio.SoundPlayer

class AndroidSoundPlayer : SoundPlayer {
    override fun play(path: String) {
        val player = MediaPlayer()
        player.setDataSource(path)
        player.prepare()
        player.start()
    }
}