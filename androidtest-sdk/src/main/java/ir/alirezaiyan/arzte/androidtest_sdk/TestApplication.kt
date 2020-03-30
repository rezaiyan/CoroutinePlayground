package ir.alirezaiyan.arzte.androidtest_sdk

import android.app.Application
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import ir.alirezaiyan.arzte.core.utils.ComponentsMap

class TestApplication : Application(), ComponentHolder by ComponentsMap()