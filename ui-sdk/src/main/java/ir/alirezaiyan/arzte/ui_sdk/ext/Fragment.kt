package ir.alirezaiyan.arzte.ui_sdk.ext

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ir.alirezaiyan.arzte.ui_sdk.BaseFragment

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/6/2020 11:12 AM.
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

val BaseFragment.appContext: Context get() = activity?.applicationContext!!