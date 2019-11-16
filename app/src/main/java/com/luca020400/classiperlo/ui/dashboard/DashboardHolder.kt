package com.luca020400.classiperlo.ui.dashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R

class DashboardHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: String, listener: (String) -> Unit) {
        itemView.findViewById<TextView>(R.id.text_dashboard).text = item
        itemView.setOnClickListener { listener(item) }
    }
}