package pl.dedio.cvmultimodule.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.base.BaseFragment
import pl.dedio.cvmultimodule.base.BaseViewModel

val Fragment.activityComponent
    get() = (activity as BaseActivity).activityComponent

val Fragment.navController
    get() = findNavController()

inline fun <reified T : BaseViewModel> Fragment.getViewModel(
    factory: ViewModelProvider.Factory
) = ViewModelProvider(this, factory).get(T::class.java)

inline fun <reified T : BaseViewModel> BaseFragment.getViewModel(
    factory: ViewModelProvider.Factory = viewModelFactory
) = ViewModelProvider(this, factory).get(T::class.java)

inline fun <reified T : BaseViewModel> BaseFragment.getActivityScopedViewModel(
    factory: ViewModelProvider.Factory = viewModelFactory
) = ViewModelProvider(requireActivity(), factory).get(T::class.java)

fun <DataBinding : ViewDataBinding> Fragment.getBinding(
    viewModel: ViewModel,
    layoutId: Int,
    bindingVariable: Int? = null,
    inflater: LayoutInflater,
    container: ViewGroup?
): DataBinding {
    val viewDataBinding: DataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)

    return viewDataBinding.bindWithLifecycle(viewModel, bindingVariable, this)
}
