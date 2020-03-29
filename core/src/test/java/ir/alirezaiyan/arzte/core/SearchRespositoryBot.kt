package ir.alirezaiyan.arzte.core

import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.ext.Either
import ir.alirezaiyan.arzte.core.ext.Either.Right
import ir.alirezaiyan.arzte.core.ext.Either.Left
import ir.alirezaiyan.arzte.core.ext.Failure
import ir.alirezaiyan.arzte.core.remote.ApiService
import org.amshove.kluent.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Response

class DoctorRepositoryBot {

    private var api = mock(ApiService::class)
    @Suppress("UNCHECKED_CAST")
    private var call = mock(Call::class) as Call<List<Doctor>>
    @Suppress("UNCHECKED_CAST")
    private var doctorResponse = mock(Response::class) as Response<List<Doctor>>

    private var repository = ArzteRepository(api)


    fun doctorsReturnsThisCall(lastKey: String): DoctorRepositoryBot {
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

    fun run(response: List<Doctor>): DoctorRepositoryBot {
        val doctors = repository.doctors()

        doctors shouldBeInstanceOf Either::class.java

        when {
            doctors.isRight -> {
                doctors shouldEqual Right(response)
                doctors.either({}, {
                    it `should not be` null
                    it.count() shouldBe 1
                })
            }
            doctorResponse.isSuccessful -> {

                doctors.either({ failure ->
                    when (failure) {
                        is Failure.NetworkConnection -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java
                        is Failure.ServerError -> failure shouldBeInstanceOf Failure.ServerError::class.java
                    }
                }, {})

            }
            !doctorResponse.isSuccessful -> {
                doctors.either(
                    { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
                    {})
            }
            else -> {
                doctors.either(
                    { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
                    {})
            }
        }

        return this
    }

    fun verifyDoctors(apiKey: String) {
        verify(api).doctors(apiKey)
    }

}