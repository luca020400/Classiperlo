package com.luca020400.classiperlo.classes

import java.io.Serializable

data class Url(val url: String, var absolute: Boolean = false) : Serializable {
    init {
        if (url.startsWith("http")) {
            absolute = true
        }
    }
}