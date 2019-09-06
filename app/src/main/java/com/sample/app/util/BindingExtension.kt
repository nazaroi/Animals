package com.sample.app.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("circleImageUrl")
fun ImageView.circleLoadImage(url: String?) {
    Glide.with(context)
        .load(url).apply(RequestOptions.circleCropTransform()).into(this)
}