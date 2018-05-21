package com.chipcerio.audiorecorder

import android.widget.Toast
import java.io.File

fun MainActivity.listFiles(): MutableList<String> {
    val file = File(filesDir.absolutePath)
    return if (file.exists() && file.isDirectory) {
        file.list().toMutableList()
    } else {
        mutableListOf()
    }
}

fun MainActivity.generateFilename(): String =
    "${this.filesDir.absolutePath}/${System.currentTimeMillis()}.3gp"

fun MainActivity.audioDir(): String = "${this.filesDir.absolutePath}/"

fun MainActivity.toast(msg: String) {
    this.runOnUiThread {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}