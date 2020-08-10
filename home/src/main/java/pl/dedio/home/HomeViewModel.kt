package pl.dedio.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.dedio.cvmodels.CvUiModel
import pl.dedio.cvmultimodule.BuildConfig
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.extension.asLiveData
import pl.dedio.home.model.CvBlockListElement
import pl.dedio.home.model.CvBlockListElementMapper
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: MainApplication,
    private val loadCvDetailsUseCase: LoadCvDetailsUseCase,
    private val cvBlockListElementMapper: CvBlockListElementMapper
) : BaseViewModel(application) {

    private val _cvData = MutableLiveData<CvUiModel>()
    val cvData = _cvData.asLiveData()

    private val _cvBlocks = MutableLiveData<List<CvBlockListElement>>()
    val cvBlocks = _cvBlocks.asLiveData()

    init {
        viewModelScope.launch {
            runCatching {
                val params = LoadCvDetailsUseCase.Params(
                    BuildConfig.GITHUB_USER_NAME,
                    BuildConfig.GIST_ID,
                    BuildConfig.GIST_NAME
                )
                loadCvDetailsUseCase.execute(params)
            }.onSuccess {
                _cvData.value = it

                val mappedBlocks = cvBlockListElementMapper.mapBlocks(it.blocks)
                _cvBlocks.value = mappedBlocks
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}