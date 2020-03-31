package ir.alirezaiyan.arzte.core

import dagger.Component
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import ir.alirezaiyan.arzte.core.utils.get
import ir.alirezaiyan.arzte.core.utils.getOrCreate
import javax.inject.Singleton

interface CoreComponent {
    val arzteRepository: ArzteRepository
}

@Singleton
@Component(dependencies = [ApiDependencies::class])
interface CoreComponentImpl : CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: ApiDependencies): CoreComponent
    }
}

val ComponentHolder.coreComponent
    get() = getOrCreate {
        DaggerCoreComponentImpl.factory().create(get())
    }
