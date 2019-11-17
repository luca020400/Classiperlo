package com.luca020400.classiperlo.ui.cp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class CpViewModel : ViewModel() {
    val url = liveData {
        emit("http://www.classiperlo.altervista.org")
    }
}