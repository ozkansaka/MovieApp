package com.movieapp.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.movieapp.R
import com.movieapp.di.AppModule

fun ImageView.loadImage(url: String, @DrawableRes placeholder: Int=R.drawable.ic_launcher_background) {
    when {
        url.isEmpty() -> return
        else ->
            AppModule.providePicasso()
                .load(url)
                .placeholder(placeholder)
                .into(this)
    }
}