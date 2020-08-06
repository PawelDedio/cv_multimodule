package pl.dedio.cvapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CvApi {

    @GET("{gistId}")
    suspend fun getCvDetails(@Path("gistId") gistId: String): Response<CvApi>
}