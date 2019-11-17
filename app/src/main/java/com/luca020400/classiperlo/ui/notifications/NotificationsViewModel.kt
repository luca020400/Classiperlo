package com.luca020400.classiperlo.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class NotificationsViewModel : ViewModel() {
    val text = liveData {
        emit("This is notifications Fragment")
    }
}