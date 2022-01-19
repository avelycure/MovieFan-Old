package com.avelycure.moviefan.utils.ui

import androidx.appcompat.widget.AppCompatImageView
import com.avelycure.moviefan.R
import com.squareup.picasso.Picasso

fun loadImage(path:String, imageView: AppCompatImageView){
    Picasso.get()
        .load(path)
        .error(R.drawable.image_placeholder)
        .placeholder(R.drawable.image_placeholder)
        .into(imageView)
}
