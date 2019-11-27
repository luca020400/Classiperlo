package com.luca020400.classiperlo.ui.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebviewViewModel : ViewModel() {
    val url = MutableLiveData<String>()
}