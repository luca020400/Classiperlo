package com.luca020400.classiperlo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luca020400.classiperlo.classes.DataItem
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class DashboardViewModel : ViewModel() {
    val data = liveData(Dispatchers.IO) {
        val doc = Jsoup.connect("http://www.classiperlo.altervista.org/radar.html").get()
        doc.select("body > p > strong").forEachIndexed { index, element ->
            emit(listOf(DataItem.Year(element.text())))
            emit(doc.select("body > ul")[index].select("li").map {
                DataItem.Class(it.text())
            })
        }
    }
}
