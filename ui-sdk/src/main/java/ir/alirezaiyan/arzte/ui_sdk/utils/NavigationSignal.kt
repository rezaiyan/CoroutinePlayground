package ir.alirezaiyan.arzte.ui_sdk.utils

data class NavigationSignal<P>(val destination: Any, val params: P) : Signal()