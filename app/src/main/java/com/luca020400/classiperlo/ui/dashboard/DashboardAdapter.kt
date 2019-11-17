package com.luca020400.classiperlo.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class DashboardAdapter(private val listener: (DataItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val ITEM_VIEW_TYPE_YEAR = 0
        private const val ITEM_VIEW_TYPE_CLASS = 1
    }

    private val _data = mutableListOf<DataItem>()
    var data: Collection<DataItem>
        set(data) {
            _data.addAll(data)

            notifyDataSetChanged()
        }
        get() = _data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ITEM_VIEW_TYPE_YEAR -> DashboardYearHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_dashboard,
                    parent,
                    false
                )
            )
            ITEM_VIEW_TYPE_CLASS -> DashboardClassHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_dashboard,
                    parent,
                    false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is DashboardYearHolder -> holder.bind(_data[position] as DataItem.Year, listener)
            is DashboardClassHolder -> holder.bind(_data[position] as DataItem.Class, listener)
            else -> throw ClassCastException("Unknown class $holder")
        }

    override fun getItemCount() = _data.size

    override fun getItemViewType(position: Int) = when (_data[position]) {
        is DataItem.Year -> ITEM_VIEW_TYPE_YEAR
        is DataItem.Class -> ITEM_VIEW_TYPE_CLASS
    }
}
