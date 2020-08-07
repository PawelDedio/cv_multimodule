package pl.dedio.cvmultimodule.extension

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

fun <DataBinding : ViewDataBinding> DataBinding.bindWithLifecycle(
    viewModel: ViewModel,
    bindingVariable: Int? = null,
    lifecycleOwner: LifecycleOwner
): DataBinding {
    this.lifecycleOwner = lifecycleOwner

    if (bindingVariable != null) {
        this.setVariable(bindingVariable, viewModel)
    }

    return this
}