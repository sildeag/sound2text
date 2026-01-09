package com.sildeag.sound2text.stt

class NoOpSoundPlayer : SoundPlayer {
    override fun play(path: String) {
        println("NoOpSoundPlayer: pretend to play $path")
    }
}