package com.luca020400.classiperlo.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun <T> MutableList<T>.clearAddAll(collection: Collection<T>) {
    this.clear()
    this.addAll(collection)
}

val RecyclerView.children: List<View>
    get() = (0..childCount).map { getChildAt(it) }