package ir.alirezaiyan.arzte.ui_sdk.utils

sealed class Lce<out T> {

    open val data: T? = null

    abstract fun <R> map(f: (T) -> R): Lce<R>

    inline fun doOnData(f: (T) -> Unit) {
        if (this is Success) {
            f(data)
        }
    }

    data class Success<out T>(override val data: T) : Lce<T>() {
        override fun <R> map(f: (T) -> R): Lce<R> = Success(f(data))
    }

    data class Error(val message: String) : Lce<Nothing>() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun <R> map(f: (Nothing) -> R): Lce<R> = this
    }

    object Loading : Lce<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): Lce<R> = this
    }
}

inline fun <S> lce(crossinline f: suspend () -> S): ActionsFlow<Lce<S>> {
    return actionsFlow {
        emit { Lce.Loading }
        try {
            val result = f()
            emit { Lce.Success(result) }
        } catch (e: Exception) {
            emit { Lce.Error(e) }
        }
    }
}