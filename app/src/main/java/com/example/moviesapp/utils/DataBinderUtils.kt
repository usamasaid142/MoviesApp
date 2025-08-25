package com.example.moviesapp.utils

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions


object DataBinderUtils {
    @JvmStatic
    @BindingAdapter("imageApplyString")
    fun imageApplyString(imageView: ImageView, url: String?) {
        val requestOptions = RequestOptions().transform(GranularRoundedCorners(25f, 25f, 25f, 25f))
        Glide.with(imageView.context)
            .asDrawable()
            .load("https://image.tmdb.org/t/p/w500${url}")
            .apply(requestOptions)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("applyRating")
    fun applyRating(ratebar: RatingBar, rate: Double?) {
       ratebar.rating=rate?.toFloat()?: 1.0f
    }
}