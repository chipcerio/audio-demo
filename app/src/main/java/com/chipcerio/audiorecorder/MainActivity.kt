package com.chipcerio.audiorecorder

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    private val RC_AUDIO_PERM = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(rc: Int, perms: Array<out String>, res: IntArray) {
        super.onRequestPermissionsResult(rc, perms, res)
        EasyPermissions.onRequestPermissionsResult(rc, perms, res)
    }

    @AfterPermissionGranted(100) // because RC_AUDIO_PERM fails ¯\_(ツ)_/¯
    private fun requestAudioPermission() {
        val audioPermission = Manifest.permission.RECORD_AUDIO
        if (EasyPermissions.hasPermissions(this, audioPermission)) {
            // proceed
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
