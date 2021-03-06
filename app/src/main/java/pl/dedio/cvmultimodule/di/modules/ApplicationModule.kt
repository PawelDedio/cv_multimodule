package pl.dedio.cvmultimodule.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.util.ViewModelFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.baseContext

    @Provides
    @Singleton
    fun provideMainApplication() = application

    @Provides
    @Singleton
    fun providesViewModelFactory(
        creators: Map<Class<out BaseViewModel>,
                @JvmSuppressWildcards Provider<BaseViewModel>>
    ) = ViewModelFactory(creators)
}