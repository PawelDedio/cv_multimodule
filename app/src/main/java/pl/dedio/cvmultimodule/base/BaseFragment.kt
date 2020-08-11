package pl.dedio.cvmultimodule.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
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

    fun showSnackBar(
        message: String,
        buttonText: String,
        buttonAction: () -> Unit,
        view: View = requireView()
    ) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(buttonText) { buttonAction() }
            .show()
    }
}