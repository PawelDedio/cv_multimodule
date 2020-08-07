package pl.dedio.cvmultimodule.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.di.annotations.ViewModelKey
import pl.dedio.cvmultimodule.main.MainViewModel
import pl.dedio.cvmultimodule.util.ViewModelFactory

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): BaseViewModel
}