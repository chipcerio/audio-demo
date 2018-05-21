package com.chipcerio.audiorecorder

import android.media.MediaRecorder
import android.util.Log
import java.io.IOException


class Recorder(private var filename: String, private val listener: OnRecordingListener) {

    private val TAG = "Recorder"

    private var isRecording = false

    private var recorder: MediaRecorder? = null

    init {
        setupRecorder()
    }

    fun record() {
        isRecording = if (!isRecording) {
            setupRecorder()
            recorder?.start()
            true
        } else {
            recorder?.stop()
            recorder?.release()
            recorder?.setOnErrorListener(null)
            recorder = null
            false
        }
        listener.onRecording(isRecording)
    }

    private fun setupRecorder() {
        recorder = MediaRecorder()
        recorder?.let {
            it.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            it.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            it.setOutputFile(filename)
            it.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        }

        try {
            recorder?.prepare()
        } catch (e: IOException) {
            Log.e(TAG, "preparing recorder failed")
        }

        recorder?.setOnErrorListener { mr, what, extra ->
            Log.e(TAG, "mediaRecorder: $mr, what: $what extra: $extra")
        }
    }

    interface OnRecordingListener {
        fun onRecording(isRecording: Boolean)
    }

}