package com.luca020400.classiperlo.ui.cp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luca020400.classiperlo.classes.DataItem
import com.luca020400.classiperlo.classes.Url
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

class CpViewModel : ViewModel() {
    private val client = OkHttpClient()

    val data = liveData(Dispatchers.IO) {
        val request = Request.Builder().url("http://www.classiperlo.altervista.org")
            .get().build()
        try {
            client.newCall(request).execute().use {
                if (!it.isSuccessful) throw Exception("Unexpected code $it")

                val doc = Jsoup.parse(it.body?.string())
                emit(doc.select("#cssmenu > ul > li").filter { it.text() != "Contatto" }.map { element ->
                    DataItem(element.text(), Url(element.select("a").attr("href")))
                }.toMutableList())
            }
        } catch (e: Exception) {
        }
    }
}
