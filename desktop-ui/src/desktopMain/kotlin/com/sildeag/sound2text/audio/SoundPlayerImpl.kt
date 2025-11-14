package com.sildeag.sound2text.audio

import com.sildeag.sound2text.service.ResourceLoader
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.AudioSystem.getAudioInputStream
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine

class SoundPlayerImpl(private val resourceLoader: ResourceLoader) : SoundPlayer {
    override fun playSmartSound(fileName: String) {
        val inputStream = resourceLoader.loadAsStream(fileName)
        if (inputStream == null) {
            println(" Could not find sound file: $fileName") //⚠️
            return
        }
        val file = File(fileName)
        val extension = file.extension
        if (extension == "wav") {
            val audioInputStream = AudioSystem.getAudioInputStream(inputStream)
            val format = audioInputStream.format
            println("audio format is $format")
            val callerClassName = Throwable().stackTrace[1].className
            println("classname = $callerClassName")
            val callerClass = Class.forName(callerClassName)
            val packageName = callerClass.`package`.name // e.g. "com.sildeag.sound2text.ui"
            println("packagename = $packageName")

            val durationSeconds = audioInputStream.frameLength / format.frameRate
            audioInputStream.close()
            if (durationSeconds <= 10) {
                playWithClip(fileName)
            } else {
                playWithStream(fileName)
            }
        } else if (extension == "mp3") {
            if (!file.exists()) {
                println("File not found: $fileName")
                return
            }

            val media = Media(file.toURI().toString())
            val player = MediaPlayer(media)

            player.setOnReady {
                println("Playing: ${file.name}")
                player.play()
            }
        } else {
            println("Sound file has invalid extension")
        }
    }
    private fun playWithClip(fileName: String) {
        val inputStream = resourceLoader.loadAsStream(fileName)
        val audioStream = AudioSystem.getAudioInputStream(inputStream)
        val clip = AudioSystem.getClip()
        clip.open(audioStream)
        clip.start()
    }
    private fun playWithStream(fileName: String) {
        val inputStream = resourceLoader.loadAsStream(fileName)
        val bufferedStream = BufferedInputStream(inputStream)
        val audioInputStream = AudioSystem.getAudioInputStream(bufferedStream)
        val format = audioInputStream.format
        val info = DataLine.Info(SourceDataLine::class.java, format)
        val line = AudioSystem.getLine(info) as SourceDataLine
        line.open(format)
        line.start()
        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (audioInputStream.read(buffer).also { bytesRead = it } != -1) {
            line.write(buffer, 0, bytesRead)
        }
        line.drain()
        line.stop()
        line.close()
        audioInputStream.close()
    }
}
