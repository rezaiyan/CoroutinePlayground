package ir.alirezaiyan.arzte.core

import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import ir.alirezaiyan.arzte.core.ext.Either
import ir.alirezaiyan.arzte.core.ext.Failure
import ir.alirezaiyan.arzte.core.remote.ApiService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/29/2020 4:45 PM.
 */
@OpenForTesting
@Singleton
class ArzteRepository @Inject constructor(private val api: ApiService){

    suspend fun doctors(lastKey : String) = api.doctors(lastKey)

}