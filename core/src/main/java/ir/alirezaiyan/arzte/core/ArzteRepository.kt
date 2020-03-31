package ir.alirezaiyan.arzte.core

import ir.alirezaiyan.arzte.core.remote.DoctorsApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/29/2020 4:45 PM.
 */
@OpenForTesting
@Singleton
class ArzteRepository @Inject constructor(private val api: DoctorsApi){

    suspend fun doctors(lastKey : String) = api.doctors(lastKey)

}