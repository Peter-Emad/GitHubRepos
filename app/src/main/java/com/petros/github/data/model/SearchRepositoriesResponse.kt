package com.petros.github.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchRepositoriesResponse(
    val total_count: Int,
    val items: List<Repository>
) : Parcelable