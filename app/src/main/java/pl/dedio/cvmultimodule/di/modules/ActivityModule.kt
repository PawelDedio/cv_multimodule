package pl.dedio.cvmultimodule.di.modules

import dagger.Module
import dagger.Provides
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.di.scopes.ActivityScope

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    @ActivityScope
    internal fun provideBaseActivity() = activity
}