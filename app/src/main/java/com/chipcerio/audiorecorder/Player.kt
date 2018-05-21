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
        try {
            player?.let {
                it.setDataSource("$dir$filename")
                it.prepare()
                it.start()
            }
        } catch (e: IOException) {
            Log.e(TAG, "preparing player failed")
        }
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}