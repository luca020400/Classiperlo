package com.luca020400.classiperlo

import android.app.Activity
import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luca020400.classiperlo.ui.setup.SetupActivity


class MainActivity : AppCompatActivity() {
    private val sTAG = MainActivity::class.java.simpleName

    companion object {
        const val REQUEST_CODE_INTRO = 1
        const val PREF_KEY_FIRST_START = "PREF_KEY_FIRST_START"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cp, R.id.navigation_dashboard, R.id.navigation_contact
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val firstStart =
            PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(PREF_KEY_FIRST_START, true)

        if (firstStart) {
            val intent = Intent(this, SetupActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_INTRO)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_INTRO) {
            if (resultCode == Activity.RESULT_OK) {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean(PREF_KEY_FIRST_START, false)
                    .apply()
            } else {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean(PREF_KEY_FIRST_START, true)
                    .apply()
                // User cancelled the intro so we'll finish this activity too.
                finish()
            }
        }
    }

    fun downloadFileAsk(url: String, contentDisposition: String?, mimeType: String?) {
        val fileName = URLUtil.guessFileName(url, contentDisposition, mimeType)

        AlertDialog.Builder(this)
            .setTitle(R.string.download_title)
            .setMessage(getString(R.string.download_message, fileName))
            .setPositiveButton(
                getString(R.string.download_positive)
            ) { _, _ -> fetchFile(url, fileName) }
            .setNegativeButton(
                getString(R.string.dismiss)
            ) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun fetchFile(url: String, fileName: String) {
        val request: DownloadManager.Request

        try {
            request = DownloadManager.Request(Uri.parse(url))
        } catch (e: IllegalArgumentException) {
            Log.e(sTAG, "Cannot download non http or https scheme")
            return
        }

        // Let this downloaded file be scanned by MediaScanner - so that it can
        // show up in Gallery app, for example.
        request.setNotificationVisibility(
            DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
        )
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        request.setMimeType(
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                MimeTypeMap.getFileExtensionFromUrl(url)
            )
        )
        getSystemService(this, DownloadManager::class.java)?.enqueue(request)
    }
}
