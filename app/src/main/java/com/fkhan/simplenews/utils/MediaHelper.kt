package com.fkhan.simplenews.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object MediaHelper {

    fun loadImage(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .into(view)
    }
}