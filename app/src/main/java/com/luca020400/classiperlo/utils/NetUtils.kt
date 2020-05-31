package com.luca020400.classiperlo.utils

import com.luca020400.classiperlo.classes.DataItem
import com.luca020400.classiperlo.classes.Url
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

object NetUtils {
    fun getClasses(): List<DataItem> {
        val client = OkHttpClient()

        val request = Request.Builder().url("http://www.classiperlo.altervista.org/radar.html")
            .get().build()
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw Exception("Unexpected code $response")

                val doc = Jsoup.parse(response.body?.string())
                return doc.select("body > ul").reversed().first().select("li").map {
                    DataItem(it.text(), Url(it.select("a").attr("href")))
                }
            }
        } catch (e: Exception) {
        }
        return emptyList()
    }
}
