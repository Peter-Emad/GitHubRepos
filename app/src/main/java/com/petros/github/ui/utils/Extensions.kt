package com.petros.github.ui.utils

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow

fun ImageView.loadUrl(url: String) {
    //todo replace with progress placeholder
    Glide.with(context)
        .load(url)
        //    .placeholder(R.drawable.ic_picture)
        .into(this)
}