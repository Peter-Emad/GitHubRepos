package com.petros.github.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petros.github.R
import com.petros.github.ui.adapters.RepositoriesAdapter
import com.petros.github.ui.viewmodels.CONTENT_STATE_VIEW_FLIPPER_ID
import com.petros.github.ui.viewmodels.ScreenState

@BindingAdapter("editTextWatcher")
internal fun AppCompatEditText.bindTextWatcher(pushNewQuery: (String?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            pushNewQuery(p0.toString())
        }
    })
}

@BindingAdapter("updateState")
internal fun ViewFlipper.bindUpdatedState(screenState: ScreenState) {
    if (screenState.viewId == CONTENT_STATE_VIEW_FLIPPER_ID)
        visibility = View.GONE
    else
        displayedChild = screenState.viewId
}

@BindingAdapter("setAdapter")
internal fun RecyclerView.bindAdapter(repositoriesAdapter: RepositoriesAdapter?) {
    repositoriesAdapter?.let { safeAdapter ->
        adapter = safeAdapter
    }
}

@BindingAdapter("resultsCount")
internal fun TextView.bindResultsCount(resultsCount: Int?) {
    resultsCount?.let { safeCount ->
        text = context.resources.getQuantityString(R.plurals.search_results_count, safeCount, safeCount)
    }
}