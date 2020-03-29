package ir.alirezaiyan.arzte

import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_sdk.BaseFragment
import ir.alirezaiyan.arzte.ui_sdk.NavigationController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidNavigationController @Inject constructor() : NavigationController {

    override fun showError(fragment: FragmentActivity, error: String?) {
        Snackbar.make(fragment.findViewById(android.R.id.content), error
                ?: "Error", Snackbar.LENGTH_LONG).show()
    }

    override fun navigateDetail(fragment: BaseFragment, doctor: Doctor) {

    }
}