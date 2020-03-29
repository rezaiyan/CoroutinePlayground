package ir.alirezaiyan.arzte.core.remote

import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DoctorsApi {
    companion object {
        private const val PARAM_KEY = "paramKey"
        private const val DOCTORS = "/interviews/challenges/android/doctors{$PARAM_KEY}.json"
    }

    @GET(DOCTORS)
    suspend fun doctors(@Path(PARAM_KEY) path: String?): DoctorResponse
}
