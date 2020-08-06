package pl.dedio.cvmultimodule.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.dedio.cvmultimodule.MainApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.baseContext

    @Provides
    @Singleton
    fun provideMainApplication() = application
}