package com.movieapp.extensions

import android.widget.ImageView
import androidx.annotation.ColorRes
import com.movieapp.R
import com.movieapp.di.AppModule

fun ImageView.loadImage(url: String, @ColorRes placeholder: Int = R.color.bg) {
    when {
        url.isEmpty() -> return
        else ->
            AppModule.providePicasso()
                .load(url)
                .placeholder(placeholder)
                .into(this)
    }
}