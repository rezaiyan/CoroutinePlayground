package ir.alirezaiyan.arzte.core.utils

sealed class State<out T> {

    open val data: T? = null

    abstract fun <R> map(f: (T) -> R): State<R>

    inline fun doOnData(f: (T) -> Unit) {
        if (this is Success) {
            f(data)
        }
    }

    data class Success<out T>(override val data: T) : State<T>() {
        override fun <R> map(f: (T) -> R): State<R> = Success(f(data))
    }

    data class Error(val message: String) : State<Nothing>() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun <R> map(f: (Nothing) -> R): State<R> = this
    }

    object Loading : State<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): State<R> = this
    }
}

inline fun <S> state(crossinline f: suspend () -> S): ActionsFlow<State<S>> {
    return actionsFlow {
        emit { State.Loading }
        try {
            val result = f()
            emit { State.Success(result) }
        } catch (e: Exception) {
            emit { State.Error(e) }
        }
    }
}