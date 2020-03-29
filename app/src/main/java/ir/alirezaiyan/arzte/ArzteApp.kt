package ir.alirezaiyan.arzte

import android.app.Application
import ir.alirezaiyan.arzte.core.ApiDependencies
import ir.alirezaiyan.arzte.core.DaggerApiComponent
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import ir.alirezaiyan.arzte.core.utils.ComponentsMap
import ir.alirezaiyan.arzte.core.utils.provide
import ir.alirezaiyan.arzte.ui_sdk.UiSdkDependencies
import timber.log.Timber


class ArzteApp : Application(), ComponentHolder by ComponentsMap() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        provide<UiSdkDependencies> {
            object : UiSdkDependencies {
                override val navigationController = AndroidNavigationController()
            }
        }
        provide<ApiDependencies> {
            DaggerApiComponent.create()
        }

    }
}
