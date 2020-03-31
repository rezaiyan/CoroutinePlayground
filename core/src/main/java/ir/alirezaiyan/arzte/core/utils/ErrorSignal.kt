package ir.alirezaiyan.arzte.core.utils


data class ErrorSignal(val error: Throwable?, val message: String) : Signal() {
    constructor(t: Throwable) : this(t, t.message ?: "Error ${t.javaClass.name}")
}