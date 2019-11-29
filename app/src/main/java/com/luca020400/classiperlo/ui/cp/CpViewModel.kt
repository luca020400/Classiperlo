package com.luca020400.classiperlo.ui.cp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luca020400.classiperlo.classes.DataItem
import com.luca020400.classiperlo.classes.Url
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class CpViewModel : ViewModel() {
    val data = liveData(Dispatchers.IO) {
        val doc = Jsoup.connect("http://www.classiperlo.altervista.org/index.html").get()
        emit(doc.select("#cssmenu > ul > li").drop(1).map {
            DataItem(it.text(), Url(it.select("a").attr("href")))
        })
    }
}
