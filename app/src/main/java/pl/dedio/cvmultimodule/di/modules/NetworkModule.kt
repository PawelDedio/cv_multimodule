package pl.dedio.cvmultimodule.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalDate
import pl.dedio.cvmultimodule.extension.applyDefaultConfiguration
import pl.dedio.cvmultimodule.network.typeAdapters.LocalDateTypeAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGsonBuilder(localDateTypeAdapter: LocalDateTypeAdapter) =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter)

    @Provides
    @Singleton
    fun providesGson(builder: GsonBuilder) = builder.create()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        context: Context
    ) = OkHttpClient.Builder().apply {
        applyDefaultConfiguration(loggingInterceptor)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder()
        .client(okHttpClient)
}