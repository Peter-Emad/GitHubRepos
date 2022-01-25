package com.petros.github.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    val full_name: String,
    val description: String? = null,
    val owner: Owner,
    val open_issues_count: String,
    val stargazers_count: String,
    val forks_count: String,
    val language: String
) : Parcelable