package pl.dedio.cvmultimodule.main

import android.os.Bundle
import pl.dedio.cvmultimodule.R
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.databinding.ActivityMainBinding
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.extension.getBinding
import pl.dedio.cvmultimodule.extension.getViewModel

class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel

    override fun makeInject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
        val binding = getBinding<ActivityMainBinding>(viewModel, R.layout.activity_main)
        setContentView(binding.root)
    }
}