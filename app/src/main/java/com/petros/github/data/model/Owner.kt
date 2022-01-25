package com.petros.github.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val avatar_url: String,
    val url: String,
    val login: String
) : Parcelable