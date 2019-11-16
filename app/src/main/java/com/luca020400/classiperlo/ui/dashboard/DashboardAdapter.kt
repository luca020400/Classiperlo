package com.luca020400.classiperlo.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.utils.clearAddAll

class DashboardAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<DashboardHolder>() {
    private val _data = mutableListOf<String>()
    var data: Collection<String>
        set(data) {
            _data.clearAddAll(data)

            notifyDataSetChanged()
        }
        get() = _data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DashboardHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_dashboard,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) =
        holder.bind(_data[position], listener)

    override fun getItemCount() = _data.size
}