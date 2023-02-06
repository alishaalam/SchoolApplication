package com.app.schoolapplication.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.app.schoolapplication.domain.model.School

@BindingAdapter("android:visibility")
fun hideIfEmpty(view: View, schoolsList : List<School>) {
    view.visibility = if (schoolsList.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("android:visibility")
fun showOnLoading(view: View, isLoading : Boolean) {
    view.visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("android:visibility")
fun showOnError(view: View, errorMessage : String?) {
    view.visibility = if (errorMessage != null) View.VISIBLE else View.GONE
}

