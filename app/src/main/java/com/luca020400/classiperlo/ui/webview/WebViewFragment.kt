package com.luca020400.classiperlo.ui.webview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.luca020400.classiperlo.MainActivity
import com.luca020400.classiperlo.R

class WebViewFragment : Fragment() {

    private val webViewViewModel by viewModels<WebViewViewModel>()
    private val webViewFragmentArgs by navArgs<WebViewFragmentArgs>()
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                findNavController().popBackStack()
            }
        }

        val root = inflater.inflate(R.layout.fragment_webview, container, false)
        webView = root.findViewById(R.id.webview_webview)
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
                userAgentString += "-classiperlo"
            }
        }

        webViewViewModel.url.observe(viewLifecycleOwner, Observer {
            if (it.absolute) {
                webView.loadUrl(it.url)
            } else {
                webView.loadUrl("http://www.classiperlo.altervista.org/${it.url}")
            }
        })
        webViewViewModel.url.value = webViewFragmentArgs.url
        return root
    }
}