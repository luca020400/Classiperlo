package com.luca020400.classiperlo.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _data = MutableLiveData<List<String>>().apply {
        value = listOf("2018/2019", "2019/2020")
    }
    val data: LiveData<List<String>> = _data
}