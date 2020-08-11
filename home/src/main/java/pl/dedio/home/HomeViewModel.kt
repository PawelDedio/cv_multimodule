package pl.dedio.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.dedio.cvmodels.CvUiModel
import pl.dedio.cvmultimodule.BuildConfig
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseViewModel
import pl.dedio.cvmultimodule.extension.asLiveData
import pl.dedio.cvmultimodule.util.ErrorFormatter
import pl.dedio.cvmultimodule.util.ResourceRepository
import pl.dedio.home.model.CvBlockListElement
import pl.dedio.home.model.CvBlockListElementMapper
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: MainApplication,
    private val loadCvDetailsUseCase: LoadCvDetailsUseCase,
    private val cvBlockListElementMapper: CvBlockListElementMapper,
    private val errorFormatter: ErrorFormatter,
    private val resourceRepository: ResourceRepository
) : BaseViewModel(application) {

    private val _cvData = MutableLiveData<CvUiModel>()
    val cvData = _cvData.asLiveData()

    private val _cvBlocks = MutableLiveData<List<CvBlockListElement>>()
    val cvBlocks = _cvBlocks.asLiveData()

    private val _error = MutableLiveData<RequestError>()
    val error = _error.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    init {
        loadCvData()
    }

    private fun loadCvData() {
        _isLoading.value = true

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
                _isLoading.value = false
            }.onFailure {
                val errorMessage = errorFormatter.getMessage(it)
                val errorButton =
                    resourceRepository.getString(R.string.cv_request_error_try_again_button)
                _error.value = RequestError(errorMessage, errorButton, ::loadCvData)
                _isLoading.value = false
            }
        }
    }

    data class RequestError(
        val message: String,
        val buttonText: String,
        val buttonAction: () -> Unit
    )
}