package com.luca020400.classiperlo.classes

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class Url(val url: String, val absolute: Boolean = url.startsWith("http")) : Parcelable
