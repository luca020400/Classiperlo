package com.luca020400.classiperlo.ui.cp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class CpAdapter : RecyclerView.Adapter<CpClassHolder>() {
    var data = mutableListOf<DataItem>()
        set(value) {
            field.clear()
            field.addAll(value)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CpClassHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_cp,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CpClassHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount() = data.size
}
