package pl.dedio.cvmultimodule.main

import android.os.Bundle
import pl.dedio.cvmultimodule.R
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.di.components.ActivityComponent

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun makeInject(component: ActivityComponent) {
        component.inject(this)
    }
}