package pl.dedio.cvmultimodule.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import pl.dedio.cvmultimodule.di.Injector
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.util.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val activityComponent: ActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        Injector.getInstance(this).activityComponent
    }

    abstract fun makeInject(component: ActivityComponent)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeInject(activityComponent)
    }
}