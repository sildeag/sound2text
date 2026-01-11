package com.sildeag.sound2text.vosk.engine.stt

import java.io.File
import javax.sound.sampled.AudioSystem
class DesktopSoundPlayer : SoundPlayer {
    override fun play(path: String) {
        val file = File(path)
        if (!file.exists()) {
            println("DesktopSoundPlayer: File not found: $path")
            return
        }
        val audio = AudioSystem.getAudioInputStream(file)
        val clip = AudioSystem.getClip()
        clip.open(audio)
        clip.start()
    }
}