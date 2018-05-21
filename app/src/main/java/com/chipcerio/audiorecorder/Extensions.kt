package com.chipcerio.audiorecorder


fun MainActivity.generateFilename(): String {
    return this.filesDir.absolutePath + "/" + System.currentTimeMillis().toString() + ".3gp"
}