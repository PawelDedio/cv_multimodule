package pl.dedio.cvmultimodule.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.extension.activityComponent
import pl.dedio.cvmultimodule.util.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeInject(activityComponent)
        initViewModel()
    }

    abstract fun makeInject(component: ActivityComponent)
    abstract fun initViewModel()

    fun <T> LiveData<T>.observe(onChanged: (T) -> Unit) =
        observe(viewLifecycleOwner, Observer(onChanged))
}