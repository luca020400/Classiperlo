package com.luca020400.classiperlo.ui.cp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class CpClassHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: DataItem) {
        itemView.findViewById<TextView>(R.id.text_cp).text = item.name
        val bundle = Bundle().also { it.putString("url", item.url) }
        itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.navigation_webview,
                bundle
            )
        )
    }
}