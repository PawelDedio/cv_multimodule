package pl.dedio.cvmultimodule.main

import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(mainApplication: MainApplication) :
    BaseViewModel(mainApplication)