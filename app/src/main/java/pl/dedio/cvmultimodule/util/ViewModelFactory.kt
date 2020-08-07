package pl.dedio.cvmultimodule.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.dedio.cvmultimodule.base.BaseViewModel
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val creators: Map<Class<out BaseViewModel>,
            @JvmSuppressWildcards Provider<BaseViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val castedModelClass = modelClass as Class<out BaseViewModel>
        val creator = creators[castedModelClass]
            ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return try {
            creator.get() as T
        } catch (e: ClassCastException) {
            throw IllegalStateException(e)
        }
    }
}