package com.luca020400.classiperlo.classes

sealed class DataItem {
    data class Class(val name: String) : DataItem()
    data class Year(val name: String) : DataItem()
}