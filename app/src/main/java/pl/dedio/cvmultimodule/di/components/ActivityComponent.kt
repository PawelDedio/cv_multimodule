package pl.dedio.cvmultimodule.di.components

import com.google.gson.GsonBuilder
import dagger.Subcomponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.main.MainActivity
import pl.dedio.cvmultimodule.di.modules.ActivityModule
import pl.dedio.cvmultimodule.di.scopes.ActivityScope
import pl.dedio.cvmultimodule.util.ResourceRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    val application: MainApplication

    val retrofitBuilder: Retrofit.Builder

    val okHttpClientBuilder: OkHttpClient.Builder

    val okHttpClient: OkHttpClient

    val httpLoggingInterceptor: HttpLoggingInterceptor

    val gsonBuilder: GsonBuilder

    val gsonConverterFactory: GsonConverterFactory

    val baseActivity: BaseActivity

    val resourceRepository: ResourceRepository
}