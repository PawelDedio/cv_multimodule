package pl.dedio.cvmultimodule.bindingAdapters

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pl.dedio.cvmultimodule.R

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(imageUri: Uri?) {
    Glide.with(this)
        .load(imageUri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.placeholder)
        .into(this)
}