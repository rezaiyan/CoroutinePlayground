package ir.alirezaiyan.arzte.ui_sdk

import androidx.fragment.app.FragmentActivity
import ir.alirezaiyan.arzte.core.entity.Doctor

interface NavigationController {
    fun navigateDetail(fragment: BaseFragment, doctor: Doctor)
    fun showError(fragment: FragmentActivity, error: String?)
}

