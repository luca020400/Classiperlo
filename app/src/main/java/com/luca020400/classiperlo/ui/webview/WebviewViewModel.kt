package com.luca020400.classiperlo.ui.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luca020400.classiperlo.classes.Url

class WebviewViewModel : ViewModel() {
    val url = MutableLiveData<Url>()
}