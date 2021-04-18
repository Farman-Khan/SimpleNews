package com.fkhan.simplenews.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object MediaHelper {
    fun loadImage(context: Context, url: String?, view: ImageView) {
        url?.let {
            Glide.with(context)
                    .load(it)
                    .into(view)
        }
    }
}
