package ir.alirezaiyan.arzte.core

import dagger.Component
import dagger.Module
import dagger.Provides
import ir.alirezaiyan.arzte.core.remote.DoctorsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://vivy.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(DoctorsApi::class.java)
}

interface ApiDependencies {
    val api: DoctorsApi
}

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent : ApiDependencies
