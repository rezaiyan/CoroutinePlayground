package ir.alirezaiyan.arzte.ui_primary_doctor_list

import ir.alirezaiyan.arzte.core.ArzteRepository
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.utils.*
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/29/2020 8:44 PM.
 */
class PrimaryListUseCase @Inject constructor(private val repository: ArzteRepository){

    fun initialState() = PrimaryListResponse()

    fun openDetail(doctor: Doctor) = NavigationSignal("doctor", doctor)

    fun load(response: PrimaryListResponse): ActionsFlow<PrimaryListResponse> = actionsFlow<DoctorsViewState> {
            response.state.doOnData { (_,key,_,loading) ->
                if (key == null) return@doOnData
                val shadowKey = if (key.isEmpty()) "" else "-${key}"
                if(!loading){
                    emit{ copy(loading = true) }
                    try {
                        val (lastKey, doctors) = repository.doctors(shadowKey)
                        emit {
                            DoctorsViewState(list = doctors, nextKey = lastKey, requestInvoked = true, loading = false)
                        }
                    }catch (t : Exception){
                        emit { copy(loading = false) }
                        emit(ErrorSignal(t))
                    }
                }
            }
        }.mapActions { stateAction -> copy(state = this.state.map { stateAction(it) }) }

    fun refresh(response: PrimaryListResponse): ActionsFlow<PrimaryListResponse> {
        return load(response).also {
            response.clear()
        }
    }

}