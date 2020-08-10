package pl.dedio.home

import pl.dedio.cvapi.CvApi
import pl.dedio.cvmodels.CvModelMapper
import pl.dedio.cvmodels.CvUiModel
import pl.dedio.cvmultimodule.base.useCase.ParametrizedUseCase
import pl.dedio.cvmultimodule.extension.extractModel
import javax.inject.Inject

class LoadCvDetailsUseCase @Inject constructor(
    private val cvApi: CvApi,
    private val cvModelMapper: CvModelMapper
) :
    ParametrizedUseCase<CvUiModel, LoadCvDetailsUseCase.Params> {

    override suspend fun execute(params: Params): CvUiModel {
        return with(params) {
            val apiModel = cvApi.getCvDetails(userName, gistId, gistName).extractModel()

            cvModelMapper.mapToUiModel(apiModel)
        }
    }

    data class Params(
        val userName: String,
        val gistId: String,
        val gistName: String
    )
}