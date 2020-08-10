package pl.dedio.cvapi

import pl.dedio.cvmodels.CvApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CvApi {

    @GET("{userName}/{gistId}/raw/{gistName}")
    suspend fun getCvDetails(
        @Path("userName") userName: String,
        @Path("gistId") gistId: String,
        @Path("gistName") gistName: String
    ): Response<CvApiModel>
}