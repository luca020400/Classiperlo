package com.luca020400.classiperlo.ui.cp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class CpAdapter : RecyclerView.Adapter<CpClassHolder>() {
    private val _data = mutableListOf<DataItem>()
    var data: Collection<DataItem>
        set(data) {
            _data.clear()
            _data.addAll(data)

            notifyDataSetChanged()
        }
        get() = _data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CpClassHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_cp,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CpClassHolder, position: Int) =
        holder.bind(_data[position])

    override fun getItemCount() = _data.size
}
