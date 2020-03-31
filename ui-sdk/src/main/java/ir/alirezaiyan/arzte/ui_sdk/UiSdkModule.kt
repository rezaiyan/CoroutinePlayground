package ir.alirezaiyan.arzte.ui_sdk

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ir.alirezaiyan.arzte.core.OpenForTesting
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import ir.alirezaiyan.arzte.core.utils.get
import ir.alirezaiyan.arzte.core.utils.getOrCreate
import ir.alirezaiyan.arzte.core.utils.ViewStateStoreFactory
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@OpenForTesting
@Module
internal class UiSdkModule {
    @Provides
    @Singleton
    fun providePrefs(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    fun viewStateStoreFactory() = ViewStateStoreFactory(Dispatchers.IO)
}

interface UiSdkComponent {
    val prefs: SharedPreferences

    val viewStateStoreFactory: ViewStateStoreFactory
    val navigationController: NavigationController
}

@Component(
        modules = [UiSdkModule::class],
        dependencies = [UiSdkDependencies::class]
)
@Singleton
internal interface UiSdkComponentImpl : UiSdkComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application, dependencies: UiSdkDependencies): UiSdkComponent
    }
}

interface UiSdkDependencies {
    val navigationController: NavigationController
}

val Application.uiSdkComponent
    get() = (this as ComponentHolder).getOrCreate {
        DaggerUiSdkComponentImpl.factory().create(
                this,
                this.get()
        )
    }