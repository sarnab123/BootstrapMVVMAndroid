package com.kohls.bootstrap.bootstrapapp.util

import android.databinding.BindingAdapter
import android.widget.ImageView

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        GlideApp.with(view.context).load(url).into(view)
    }
}