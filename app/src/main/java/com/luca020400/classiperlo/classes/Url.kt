package com.luca020400.classiperlo.classes

import java.io.Serializable

data class Url(val url: String, val absolute: Boolean = url.startsWith("http")) : Serializable