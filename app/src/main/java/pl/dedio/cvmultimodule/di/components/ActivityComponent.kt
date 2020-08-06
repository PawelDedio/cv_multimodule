package pl.dedio.cvmultimodule.di.components

import dagger.Subcomponent
import pl.dedio.cvmultimodule.main.MainActivity
import pl.dedio.cvmultimodule.di.modules.ActivityModule
import pl.dedio.cvmultimodule.di.scopes.ActivityScope

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}