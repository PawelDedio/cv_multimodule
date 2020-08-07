package pl.dedio.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.dedio.cvmodels.CvUiModel
import pl.dedio.cvmultimodule.BuildConfig
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.extension.asLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: MainApplication,
    private val loadCvDetailsUseCase: LoadCvDetailsUseCase
) : BaseViewModel(application) {

    private val _cvData = MutableLiveData<CvUiModel>()
    val cvData = _cvData.asLiveData()

    init {
        viewModelScope.launch {
            runCatching {
                val params = LoadCvDetailsUseCase.Params(
                    BuildConfig.GITHUB_USER_NAME,
                    BuildConfig.GIST_ID,
                    BuildConfig.GIST_RAW_ID,
                    BuildConfig.GIST_NAME
                )
                loadCvDetailsUseCase.execute(params)
            }.onSuccess {
                it
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}