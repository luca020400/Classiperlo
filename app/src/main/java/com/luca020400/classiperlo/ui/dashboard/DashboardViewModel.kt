package com.luca020400.classiperlo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luca020400.classiperlo.classes.DataItem
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class DashboardViewModel : ViewModel() {
    val data = liveData(Dispatchers.IO) {
        val doc = Jsoup.connect("http://www.classiperlo.altervista.org/radar.html").get()
        val years = doc.select("body > p > strong").reversed()
        val classes = doc.select("body > ul").reversed()
        years.forEachIndexed { index, element ->
            emit(listOf(DataItem.Year(element.text())))
            emit(classes[index].select("li").map {
                DataItem.Class(it.text())
            })
        }
    }
}
