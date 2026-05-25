package com.sildeag.sound2text.android.permissions
import android.Manifest
import androidx.activity.result.ActivityResultLauncher
class MicPermissionManager(
    private val launcher: ActivityResultLauncher<String>
) {
    fun request() {
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }
}
