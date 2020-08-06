package pl.dedio.cvmultimodule.di.annotations

import dagger.MapKey
import pl.dedio.cvmultimodule.base.BaseViewModel
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out BaseViewModel>)