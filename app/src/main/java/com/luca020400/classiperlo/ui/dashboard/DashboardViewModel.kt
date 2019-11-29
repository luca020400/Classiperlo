package com.luca020400.classiperlo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luca020400.classiperlo.classes.DataItem
import com.luca020400.classiperlo.classes.Url
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class DashboardViewModel : ViewModel() {
    val data = liveData(Dispatchers.IO) {
        val doc = Jsoup.connect("http://www.classiperlo.altervista.org/radar.html").get()
        emit(doc.select("body > ul").reversed().first().select("li").map {
            DataItem(it.text(), Url(it.select("a").attr("href")))
        })
    }
}
