package pl.dedio.home.di

import dagger.Component
import pl.dedio.cvapi.di.CvApiModule
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.di.scopes.FeatureScope
import pl.dedio.home.HomeFragment

@FeatureScope
@Component(modules = [HomeModule::class, CvApiModule::class], dependencies = [ActivityComponent::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(component: ActivityComponent): HomeComponent
    }
}