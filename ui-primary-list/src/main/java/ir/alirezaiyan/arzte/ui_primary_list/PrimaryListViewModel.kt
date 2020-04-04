package ir.alirezaiyan.arzte.ui_primary_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.alirezaiyan.arzte.core.OpenForTesting
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.utils.ViewStateStoreFactory
import javax.inject.Inject

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/29/2020 8:44 PM.
 */
@OpenForTesting
class PrimaryListViewModel  @Inject constructor(
    private val useCase : PrimaryListUseCase,
    factory: ViewStateStoreFactory
) : ViewModel() {

    val state = factory(useCase.initialState(), viewModelScope)

    fun loadDoctors() = state.dispatchActions(useCase.load(state()))

//    fun loadMore() = state.dispatchActions(useCase.loadNextPage(state()))

    fun refresh() = state.dispatchActions(useCase.refresh(state()))

    fun openDoctorDetail(doctor: Doctor) = state.dispatchSignal(useCase.openDetail(doctor))

}