package ir.alirezaiyan.arzte.ui_primary_list;

import assertk.all
import assertk.assertThat
import assertk.assertions.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import ir.alirezaiyan.arzte.core.ArzteRepository
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.entity.DoctorResponse
import ir.alirezaiyan.arzte.core.utils.ErrorSignal
import ir.alirezaiyan.arzte.core.utils.Signal
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_1
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_2
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_3
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_4
import ir.alirezaiyan.arzte.testdata.containsState
import ir.alirezaiyan.arzte.testdata.map
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

    @Test
    fun emptyStateVisible() {
        on { repository.doctors("") } doReturn DoctorResponse("", emptyList())

        val initialState = PrimaryListResponse()
        val states = useCase.load(initialState)
            .states(initialState)
            .filterIsInstance<PrimaryListResponse>().map { it.state }

        assertThat(states).all {
            containsState("SS")

            map { it.data }.all {
                map { it?.emptyStateVisible ?: false }
                    .containsExactly(false, true)

                transform { it.last()?.list }
                    .isNotNull().isEmpty()
            }
        }
    }

    private fun response(repo1: Doctor, repo2: Doctor, nextPage: String): DoctorResponse {
        return DoctorResponse(nextPage,listOf(repo1, repo2))
    }

    @Test
    fun loadMore() {
        on { repository.doctors("") } doReturn response(DOCTOR_1, DOCTOR_2, "abcd")
        on { repository.doctors("-abcd") } doReturn response(DOCTOR_3, DOCTOR_4, "efgh")

        val initialState = PrimaryListResponse()
        val lastState = useCase.load(initialState)
            .states(initialState)
            .filterIsInstance<PrimaryListResponse>().last()

        val states = useCase.loadMore(lastState)
            .states(lastState)
            .filterIsInstance<PrimaryListResponse>()

        assertThat(states.map { it.state }).all {
            containsState("SS")

            map { it.data?.loading ?: false }
                .containsExactly(true, false)

            transform { it.last().data!!.list }
                .isEqualTo(listOf(DOCTOR_1, DOCTOR_2, DOCTOR_3, DOCTOR_4))
        }

    }

    @Test
    fun errorLoadingMore() {
        on { repository.doctors("") } doReturn response(DOCTOR_1, DOCTOR_2, "abcd")
        on { repository.doctors("-abcd") } doThrow RuntimeException("error")

        val initialState = PrimaryListResponse()
        val lastState = useCase.load(initialState)
            .states(initialState)
            .filterIsInstance<PrimaryListResponse>().last()

        val states = useCase.loadMore(lastState)
            .states(lastState)
            .filterIsInstance<PrimaryListResponse>()

        assertThat(states.map { it.state }).containsState("SS")

        assertThat(states.map { it.state.data?.loading ?: false })
            .containsExactly(true, false)

        assertThat(states.last().state.data?.list).isNotNull().containsExactly(DOCTOR_1, DOCTOR_2)

        val signals = useCase.loadMore(lastState)
            .states(lastState)
            .filterIsInstance<Signal>()

        assertThat(signals.last())
            .isInstanceOf(ErrorSignal::class)
            .prop(ErrorSignal::message)
            .isEqualTo("error")
    }

}