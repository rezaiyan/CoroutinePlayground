package ir.alirezaiyan.arzte.ui_primary_doctor_list

import ir.alirezaiyan.arzte.core.ArzteRepository
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_sdk.utils.*
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/29/2020 8:44 PM.
 */
class PrimaryListUseCase @Inject constructor(private val repository: ArzteRepository){

    fun initialState() = RequestViewState()

    fun openDetail(doctor: Doctor) = NavigationSignal("doctor", doctor)

    fun loadNextPage(state: RequestViewState): ActionsFlow<RequestViewState> = actionsFlow<DoctorsViewState> {
            state.doctors.doOnData { (_,key,_,loading) ->
                if(key.isNullOrEmpty() && !loading){
                    emit{ copy(loading = true) }
                    try {
                        val (lastKey, doctors) = repository.doctors(key!!)
                        emit {
                            DoctorsViewState(list = doctors, nextKey = lastKey, requestInvoked = true, loading = true)
                        }
                    }catch (t : Exception){
                        emit { copy(loading = false) }
                        emit(ErrorSignal(t))
                    }
                }
            }
        }.mapActions { stateAction -> copy(doctors = doctors.map { stateAction(it) }) }

    fun load(state: RequestViewState): ActionsFlow<RequestViewState> = lce {
        val (lastKey, doctors) = repository.doctors(state.nextKey)
        DoctorsViewState(list = doctors, nextKey = lastKey, requestInvoked = true, loading = true)
    }.mapActions { stateAction -> copy(doctors = stateAction(doctors)) }

    fun refresh(state: RequestViewState): ActionsFlow<RequestViewState> {
        return load(state).also {
            state.clear()
        }
    }

}