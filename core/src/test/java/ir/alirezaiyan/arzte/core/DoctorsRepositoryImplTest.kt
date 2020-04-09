package ir.alirezaiyan.arzte.core

import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.testdata.UnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/10/2020 12:35 AM.
 */
class DoctorsRepositoryImplTest : UnitTest() {

    private val lastKey = ""
    private val mockResponse = listOf(Doctor())

    @Test
    fun serverErrorTest() = runBlocking{
        DoctorRepositoryBot()
            .responseBeSuccessful(false)
            .warmUp()
            .runWith(lastKey,mockResponse)
            .verifyDoctors(lastKey)
    }

    @Test
    fun successfulResultTest() = runBlocking {
        DoctorRepositoryBot()
            .responseBeSuccessful(true)
            .doctorsReturnsThisCall(lastKey)
            .responseBodyReturns(mockResponse)
            .warmUp()
            .runWith(lastKey, mockResponse)
            .verifyDoctors(lastKey)
    }

}