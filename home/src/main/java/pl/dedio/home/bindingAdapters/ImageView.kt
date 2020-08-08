package pl.dedio.home.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import pl.dedio.cvmultimodule.bindingAdapters.loadImage

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String) = loadImage(imageUrl)