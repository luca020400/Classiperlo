package com.luca020400.classiperlo.utils

fun <T> MutableList<T>.clearAddAll(collection: Collection<T>) {
    this.clear()
    this.addAll(collection)
}