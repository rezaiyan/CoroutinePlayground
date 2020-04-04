package ir.alirezaiyan.arzte.testdata

import assertk.Assert
import assertk.assertions.isEqualTo
import ir.alirezaiyan.arzte.core.utils.ActionsFlow
import ir.alirezaiyan.arzte.core.utils.State
import ir.alirezaiyan.arzte.core.utils.StateAction
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing


fun Assert<List<Any>>.containsState(expected: String) = given { actual ->
    val actualString = actual.joinToString("") {
        when (it) {
            is State.Success<*> -> "S"
            is State.Loading -> "L"
            is State.Error -> "E"
            else -> "N"
        }
    }
    assertThat(actualString).isEqualTo(expected)
}

fun <T, P> Assert<List<T>>.map(extract: (T) -> P): Assert<List<P>> =
        transform { it.map(extract) }

fun <T> on(methodCall: suspend () -> T): OngoingStubbing<T> {
    return Mockito.`when`(runBlocking { methodCall() })!!
}

fun <T : Any> ActionsFlow<T>.states(initialState: T): List<Any> = runBlocking {
    fold(initialState to emptyList<Any>()) { (prevState, states), action ->
        if (action is StateAction) {
            val curState = action(prevState)
            curState to states + curState
        } else
            prevState to states + action
    }.second
}