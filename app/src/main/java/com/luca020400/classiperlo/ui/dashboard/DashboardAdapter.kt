package com.luca020400.classiperlo.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class DashboardAdapter : RecyclerView.Adapter<DashboardClassHolder>() {
    var data = mutableListOf<DataItem>()
        set(value) {
            field.clear()
            field.addAll(value)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DashboardClassHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_dashboard,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DashboardClassHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount() = data.size
}
