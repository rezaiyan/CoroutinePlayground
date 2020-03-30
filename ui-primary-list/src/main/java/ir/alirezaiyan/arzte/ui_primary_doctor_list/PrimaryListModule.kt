package ir.alirezaiyan.arzte.ui_primary_doctor_list

import android.app.Application
import androidx.fragment.app.Fragment
import dagger.Component
import ir.alirezaiyan.arzte.core.CoreComponent
import ir.alirezaiyan.arzte.core.coreComponent
import ir.alirezaiyan.arzte.core.utils.ComponentHolder
import ir.alirezaiyan.arzte.core.utils.getOrCreate
import ir.alirezaiyan.arzte.ui_sdk.*

@FeatureAppScope
@Component(dependencies = [UiSdkComponent::class, CoreComponent::class])
interface PrimaryListAppComponent: UiSdkComponent {
    val primaryListUseCase: PrimaryListUseCase

    @Component.Factory
    interface Factory {
        fun create(core: CoreComponent, viewLib: UiSdkComponent): PrimaryListAppComponent
    }
}

val Application.primaryListComponent
    get() = (this as ComponentHolder).getOrCreate {
        DaggerPrimaryListAppComponent.factory()
                .create(coreComponent, uiSdkComponent)
    }

@FeatureFragmentScope
@Component(dependencies = [PrimaryListAppComponent::class])
interface PrimaryListFragmentComponent {
    val viewModel: PrimaryListViewModel

    @Component.Factory
    interface Factory {
        fun create(appComponent: PrimaryListAppComponent): PrimaryListFragmentComponent
    }
}

val Fragment.primaryListFragmentComponent: PrimaryListFragmentComponent
    get() = getOrCreateFragmentComponent {
        DaggerPrimaryListFragmentComponent.factory()
                .create(requireActivity().application.primaryListComponent)
    }