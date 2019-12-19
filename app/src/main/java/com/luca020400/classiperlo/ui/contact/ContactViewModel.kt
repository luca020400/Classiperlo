package com.luca020400.classiperlo.ui.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    enum class Options(val value: Int) {
        Message(0),
        NoMailMessage(1),
        AnonymousMessage(2);

        companion object {
            private val map = values().associateBy(Options::value)
            fun fromInt(type: Int) = map[type]
        }
    }

    val option = MutableLiveData<Options>().apply { postValue(Options.Message) }
}