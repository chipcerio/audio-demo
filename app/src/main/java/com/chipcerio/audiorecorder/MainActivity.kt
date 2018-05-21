package com.chipcerio.audiorecorder

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class MainActivity : AppCompatActivity(), Recorder.OnRecordingListener, AudioAdapter.OnAudioClickListener {

    private val TAG = "MainActivity"

    private val RC_AUDIO_PERM = 100

    private lateinit var recorder: Recorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        recorder = Recorder(generateFilename(), this)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = AudioAdapter(listFiles(), this@MainActivity)
        }
    }

    private fun listFiles(): MutableList<String> {
        val file = File(filesDir.absolutePath)
        return if (file.exists() && file.isDirectory) {
            file.list().toMutableList()
        } else {
            mutableListOf()
        }
    }

    override fun onRecording(isRecording: Boolean) {
        Log.d(TAG, "isRecording: $isRecording")
    }

    override fun onAudioClick(filename: String) {
        Log.d(TAG, "onAudioClick: $filename")
    }

    override fun onRequestPermissionsResult(rc: Int, perms: Array<out String>, res: IntArray) {
        super.onRequestPermissionsResult(rc, perms, res)
        EasyPermissions.onRequestPermissionsResult(rc, perms, res)
    }

    @AfterPermissionGranted(100) // because RC_AUDIO_PERM fails ¯\_(ツ)_/¯
    private fun requestAudioPermission() {
        val audioPermission = Manifest.permission.RECORD_AUDIO
        if (EasyPermissions.hasPermissions(this, audioPermission)) {
            recorder.record()
        } else {
            EasyPermissions.requestPermissions(this,
                getString(R.string.audio_permission_rationale), RC_AUDIO_PERM, audioPermission)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.record_audio) {
            requestAudioPermission()
        }
        return super.onOptionsItemSelected(item)
    }
}
