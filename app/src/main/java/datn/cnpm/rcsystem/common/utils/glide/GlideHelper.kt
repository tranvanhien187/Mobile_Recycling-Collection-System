package com.example.basesource.common.utils.glide

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideHelper {
    fun loadImage(url: String, imageView: ImageView, resourceId: Int) {
        if (imageView.context == null) {
            return
        }
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions().placeholder(resourceId)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            )
            .into(imageView)
    }

    fun loadCaptcha(url: Bitmap, captcha: ImageView) {
        if (captcha.context == null) {
            return
        }
        Glide.with(captcha.context)
            .load(url)
            .into(captcha)
    }

}
