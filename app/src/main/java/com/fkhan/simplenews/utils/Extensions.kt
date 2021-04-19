package com.fkhan.simplenews.utils

import android.graphics.Typeface
import android.widget.TextView

fun TextView.setFont(font : String) {
    this.apply {
        typeface = Typeface.createFromAsset(this.context.assets, font)
    }
}