package pl.dedio.cvapi.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.dedio.cvapi.CvApi
import pl.dedio.cvmodels.blocks.baseBlockTypeAdapter
import pl.dedio.cvmultimodule.BuildConfig
import pl.dedio.cvmultimodule.di.scopes.FeatureScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class CvApiModule {

    @Provides
    @FeatureScope
    fun providesGson(builder: GsonBuilder): Gson {
        builder.registerTypeAdapterFactory(baseBlockTypeAdapter)

        return builder.create()
    }

    @Provides
    @FeatureScope
    @Named("cvApi")
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @FeatureScope
    fun providesCvApi(
        builder: Retrofit.Builder,
        @Named("cvApi") gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): CvApi {
        val retrofit = builder.baseUrl(BuildConfig.CV_API_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(CvApi::class.java)
    }
}