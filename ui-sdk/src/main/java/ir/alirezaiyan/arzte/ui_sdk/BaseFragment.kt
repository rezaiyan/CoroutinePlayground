package ir.alirezaiyan.arzte.ui_sdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ir.alirezaiyan.arzte.core.ext.Failure
import ir.alirezaiyan.arzte.ui_sdk.ext.appContext
import ir.alirezaiyan.base.BaseActivity
import kotlinx.android.synthetic.main.activity_layout.*

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/6/2020 11:08 AM.
 */

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId(), container, false)
}