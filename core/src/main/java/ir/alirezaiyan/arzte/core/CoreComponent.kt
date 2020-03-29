package ir.alirezaiyan.arzte.core

import dagger.Component
import dagger.Module
import dagger.Provides
import ir.alirezaiyan.arzte.core.remote.ApiService
import ir.alirezaiyan.arzte.core.utils.get
import ir.alirezaiyan.arzte.core.utils.getOrCreate
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

interface CoreComponent {
    val arzteRepository: ArzteRepository
}

@Singleton
@Component(dependencies = [ApiDependencies::class])
interface CoreComponentImpl : CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: ApiDependencies): CoreComponent
    }
}

val ComponentHolder.coreComponent
    get() = getOrCreate {
        DaggerCoreComponentImpl.factory().create(get())
    }
