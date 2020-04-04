package ir.alirezaiyan.arzte.ui_primary_list;

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isNotNull
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import ir.alirezaiyan.arzte.core.ArzteRepository
import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_1
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_2
import ir.alirezaiyan.arzte.testdata.containsState
import ir.alirezaiyan.arzte.testdata.on
import ir.alirezaiyan.arzte.testdata.states
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 4/1/2020 10:11 PM.
 */
class PrimaryListUseCaseTest {

    private val repository : ArzteRepository = mock()
    private val useCase = PrimaryListUseCase(repository)

    @Test
    fun user() = runBlocking{
        on { repository.doctors("") } doReturn DoctorResponse("",listOf(DOCTOR_1, DOCTOR_2))

        val initialState = PrimaryListResponse()
        val states = useCase.load(initialState)
            .states(initialState)
            .filterIsInstance<PrimaryListResponse>().map { it.state }

        assertThat(states).containsState("SS")

        assertThat(states.map { it.data?.loading ?: false })
            .containsExactly(true, false)

        assertThat(states.last().data?.list)
            .isNotNull()
            .containsExactly(DOCTOR_1, DOCTOR_2)

    }
}