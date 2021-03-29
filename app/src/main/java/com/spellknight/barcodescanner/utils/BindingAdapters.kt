
package com.spellknight.barcodescanner.utils

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.spellknight.barcodescanner.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.ic_check_white_24dp)
        .timeout(60000)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .error(R.drawable.ic_check_white_24dp)
        .into(view)
}