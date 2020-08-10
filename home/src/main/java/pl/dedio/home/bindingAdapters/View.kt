package pl.dedio.home.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import pl.dedio.cvmultimodule.bindingAdapters.visibleElseGone

@BindingAdapter("visibleElseGone")
fun View.visibleElseGone(isVisible: Boolean) = visibleElseGone(isVisible)
