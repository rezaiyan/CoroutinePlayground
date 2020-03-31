package ir.alirezaiyan.arzte.ui_sdk.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified VM : ViewModel> Fragment.viewModel(crossinline provider: () -> VM): Lazy<VM> {
    return lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
                val viewModel = provider.invoke()
                return viewModel as T1
            }
        }
        ViewModelProvider(this, factory).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModel(crossinline provider: () -> VM): Lazy<VM> {
    return lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
                val viewModel = provider.invoke()
                return viewModel as T1
            }
        }
        ViewModelProvider(this, factory).get(VM::class.java)
    }
}
