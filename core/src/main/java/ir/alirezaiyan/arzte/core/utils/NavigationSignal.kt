package ir.alirezaiyan.arzte.core.utils

data class NavigationSignal<P>(val destination: Any, val params: P) : Signal()