package com.luca020400.classiperlo

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.luca020400.classiperlo.utils.IOnBackPressed

class MainActivity : AppCompatActivity() {
    private val sTAG = MainActivity::class.java.simpleName

    private val storagePermReq = 423

    private var mWaitingDownloadFileName: String? = null
    private var mWaitingDownloadUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cp, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.let {
            it.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is IOnBackPressed) {
                    if (!fragment.onBackPressed()) {
                        super.onBackPressed()
                    }
                } else {
                    super.onBackPressed()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        results: IntArray
    ) {
        val url = mWaitingDownloadUrl
        when (requestCode) {
            storagePermReq -> {
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && url != null) {
                    downloadFileAsk(url, null, null)
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        AlertDialog.Builder(this)
                            .setTitle(R.string.permission_error_title)
                            .setMessage(R.string.permission_error_storage)
                            .setCancelable(false)
                            .setPositiveButton(
                                getString(R.string.permission_error_ask_again)
                            ) { _, _ -> requestStoragePermission() }
                            .setNegativeButton(
                                getString(R.string.dismiss)
                            ) { dialog, _ -> dialog.dismiss() }
                            .show()
                    } else {
                        Snackbar.make(
                            findViewById(R.id.container),
                            getString(R.string.permission_error_forever),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    fun downloadFileAsk(url: String, contentDisposition: String?, mimeType: String?) {
        val fileName =
            mWaitingDownloadFileName ?: URLUtil.guessFileName(url, contentDisposition, mimeType)

        if (!hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mWaitingDownloadFileName = fileName
            mWaitingDownloadUrl = url
            requestStoragePermission()
            return
        }
        mWaitingDownloadFileName = null
        mWaitingDownloadUrl = null

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

    private fun requestStoragePermission() {
        val permissionArray = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissionArray, storagePermReq)
    }

    private fun hasPermission(permission: String) =
        ActivityCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
}
