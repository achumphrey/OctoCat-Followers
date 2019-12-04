package com.example.octocatfollowers.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return  LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadGlideImage(path: String){
    Glide.with(this).load(path).into(this)
}
