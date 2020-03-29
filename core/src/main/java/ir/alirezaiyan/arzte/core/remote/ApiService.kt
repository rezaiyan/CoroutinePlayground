package ir.alirezaiyan.arzte.core.remote

import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/8/2020 1:33 AM.
 */
@Singleton
class ApiService
@Inject constructor(retrofit: Retrofit) : DoctorsApi {
    private val api by lazy { retrofit.create(DoctorsApi::class.java) }

    override suspend fun doctors(path: String?) = api.doctors(path)
}
