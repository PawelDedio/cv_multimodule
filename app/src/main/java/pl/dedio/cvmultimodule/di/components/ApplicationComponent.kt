package pl.dedio.cvmultimodule.di.components

import android.content.Context
import dagger.Component
import pl.dedio.cvmultimodule.di.modules.ActivityModule
import pl.dedio.cvmultimodule.di.modules.ApplicationModule
import pl.dedio.cvmultimodule.di.modules.NetworkModule
import pl.dedio.cvmultimodule.di.modules.ViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelsModule::class])
interface ApplicationComponent {
    fun plus(module: ActivityModule): ActivityComponent
    val context: Context
}