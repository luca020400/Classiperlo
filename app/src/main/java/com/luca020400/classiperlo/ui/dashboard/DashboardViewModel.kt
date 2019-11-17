package com.luca020400.classiperlo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class DashboardViewModel : ViewModel() {
    val data = liveData(Dispatchers.IO) {
        val doc = Jsoup.connect("http://www.classiperlo.altervista.org/radar.html").get()
        emit(doc.select("body > p > strong").map { it.text() })
    }
}