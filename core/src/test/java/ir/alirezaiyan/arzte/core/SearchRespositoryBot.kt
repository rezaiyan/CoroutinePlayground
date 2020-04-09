package ir.alirezaiyan.arzte.core

import com.nhaarman.mockitokotlin2.mock
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import ir.alirezaiyan.arzte.core.remote.DoctorsApi
import org.mockito.BDDMockito.given
import org.amshove.kluent.*
import org.mockito.Mockito
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Response

class DoctorRepositoryBot {

    private var api : DoctorsApi = mock()
    private var call : Call<List<Doctor>> = mock()
    private var doctorResponse : Response<List<Doctor>> = mock()

    private var repository = ArzteRepository(api)


    suspend fun doctorsReturnsThisCall(lastKey: String): DoctorRepositoryBot {
        Mockito.`when`(api.doctors(lastKey)).then { call }
        return this
    }

    fun responseBeSuccessful(beSuccessful: Boolean): DoctorRepositoryBot {
        given(doctorResponse.isSuccessful).willReturn(beSuccessful)
        return this
    }

    fun responseBodyReturns(response: List<Doctor>): DoctorRepositoryBot {
        given(doctorResponse.body()).willReturn(response)
        return this
    }

    fun warmUp(): DoctorRepositoryBot {
        given(call.execute()).willReturn(doctorResponse)
        return this
    }

    suspend fun runWith(key : String, response: List<Doctor>): DoctorRepositoryBot {
        val doctors = repository.doctors(key)

        doctors shouldBeInstanceOf DoctorResponse::class.java

            doctors `should not be` null
            doctors.doctors.size shouldBe 1
        return this
    }

    suspend fun verifyDoctors(apiKey: String) {
        verify(api).doctors(apiKey)
    }

}