package pl.dedio.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dedio.cvmultimodule.base.BaseFragment
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.extension.getBinding
import pl.dedio.cvmultimodule.extension.getViewModel
import pl.dedio.home.databinding.FragmentHomeBinding
import pl.dedio.home.di.DaggerHomeComponent

class HomeFragment : BaseFragment() {

    lateinit var viewModel: HomeViewModel

    override fun makeInject(component: ActivityComponent) {
        DaggerHomeComponent.factory().create(component).inject(this)
    }

    override fun initViewModel() {
        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = getBinding<FragmentHomeBinding>(
            viewModel = viewModel,
            bindingVariable = BR.viewModel, layoutId = R.layout.fragment_home,
            inflater = inflater, container = container
        )
        return binding.root
    }
}