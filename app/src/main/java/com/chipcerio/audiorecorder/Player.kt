package com.chipcerio.audiorecorder

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException


class Player(private val dir: String) {

    private val TAG = "Player"

    private var isPlaying = false

    private var player: MediaPlayer? = null

    init {
        player = MediaPlayer()
    }

    fun play(filename: String) {
        if (!isPlaying) {
            try {
                player?.let {
                    it.setDataSource("$dir$filename")
                    it.prepare()
                    it.start()
                    isPlaying = true
                }
            } catch (e: IOException) {
                Log.e(TAG, "preparing player failed")
            }
        } else {
            stop()
        }
    }

    fun stop() {
        player?.let {
            it.stop()
            it.release()
            player = null
        }
    }
}