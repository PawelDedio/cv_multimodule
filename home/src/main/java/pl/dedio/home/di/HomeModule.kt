package pl.dedio.home.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.di.annotations.ViewModelKey
import pl.dedio.cvmultimodule.di.scopes.FeatureScope
import pl.dedio.cvmultimodule.util.ViewModelFactory
import pl.dedio.home.HomeViewModel
import javax.inject.Provider

@Module
class HomeModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun providesHomeViewModel(viewModel: HomeViewModel) = viewModel as BaseViewModel

    @Provides
    @FeatureScope
    fun providesViewModelFactory(
        creators: Map<Class<out BaseViewModel>,
                @JvmSuppressWildcards Provider<BaseViewModel>>
    ) = ViewModelFactory(creators)
}