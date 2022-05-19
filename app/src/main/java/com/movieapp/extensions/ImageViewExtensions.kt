package com.movieapp.extensions

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.movieapp.R
import com.movieapp.di.AppModule

fun ImageView.loadImage(url: String, @ColorRes placeholder: Int = R.color.black) {
    when {
        url.isEmpty() -> return
        else ->
            AppModule.providePicasso()
                .load(url)
                .placeholder(placeholder)
                .into(this)
    }
}