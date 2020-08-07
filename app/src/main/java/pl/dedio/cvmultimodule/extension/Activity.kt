package pl.dedio.cvmultimodule.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.base.BaseViewModel

inline fun <reified T : BaseViewModel> AppCompatActivity.getViewModel(
    factory: ViewModelProvider.Factory
) = ViewModelProvider(this, factory).get(T::class.java)

inline fun <reified T : BaseViewModel> BaseActivity.getViewModel(
    factory: ViewModelProvider.Factory = viewModelFactory
) = ViewModelProvider(this, factory).get(T::class.java)

fun <DataBinding : ViewDataBinding> FragmentActivity.getBinding(
    viewModel: ViewModel,
    layoutId: Int,
    bindingVariable: Int? = null
): DataBinding {
    val viewDataBinding: DataBinding = DataBindingUtil.setContentView(this, layoutId)

    return viewDataBinding.bindWithLifecycle(viewModel, bindingVariable, this)
}