package com.luca020400.classiperlo.ui.cp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.luca020400.classiperlo.MainActivity
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.utils.IOnBackPressed

class CpFragment : Fragment(), IOnBackPressed {

    private val cpViewModel by viewModels<CpViewModel>()
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cp, container, false)
        webView = root.findViewById(R.id.webview_cp)
        webView.apply {
            setDownloadListener { url, _, contentDisposition, mimeType, _ ->
                (activity as MainActivity).downloadFileAsk(
                    url,
                    contentDisposition,
                    mimeType
                )
            }
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            settings.apply {
                allowFileAccess = true
                builtInZoomControls = true
                databaseEnabled = true
                displayZoomControls = false
                domStorageEnabled = true
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                setAppCacheEnabled(true)
                setAppCachePath(context.getDir("appcache", Context.MODE_PRIVATE).path)
                setGeolocationEnabled(true)
            }
        }
        cpViewModel.url.observe(viewLifecycleOwner, Observer {
            webView.loadUrl(it)
        })
        return root
    }

    override fun onBackPressed() =
        if (webView.canGoBack()) {
            webView.goBack()
            true
        } else {
            false
        }
}
