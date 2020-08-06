package pl.dedio.cvmultimodule.extension

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.dedio.cvmultimodule.BuildConfig
import java.util.concurrent.TimeUnit

fun OkHttpClient.Builder.applyDefaultConfiguration(loggingInterceptor: HttpLoggingInterceptor) {
    readTimeout(BuildConfig.API_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
    connectTimeout(BuildConfig.API_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        addInterceptor(loggingInterceptor)
    }
}