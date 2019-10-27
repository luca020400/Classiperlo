package com.luca020400.classiperlo.ui.cp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CpViewModel : ViewModel() {

    private val _url = MutableLiveData<String>().apply {
        value = "http://www.classiperlo.altervista.org"
    }
    val url: LiveData<String> = _url
}