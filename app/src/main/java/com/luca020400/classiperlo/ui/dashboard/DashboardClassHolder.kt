package com.luca020400.classiperlo.ui.dashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class DashboardClassHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: DataItem.Class, listener: (DataItem.Class) -> Unit) {
        itemView.findViewById<TextView>(R.id.text_dashboard).text = item.name
        itemView.setOnClickListener { listener(item) }
    }
}