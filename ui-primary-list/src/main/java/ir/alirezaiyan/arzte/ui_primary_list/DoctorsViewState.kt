package ir.alirezaiyan.arzte.ui_primary_list

import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.utils.State

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/30/2020 12:47 AM.
 */
data class DoctorsViewState(
    val list: List<Doctor>,
    val nextKey: String? = "",
    val requestInvoked: Boolean = false,
    val loading : Boolean = false
){
    val emptyStateVisible: Boolean = requestInvoked && list.isEmpty()
}

data class PrimaryListResponse(
    var nextKey: String = "",
    val state: State<DoctorsViewState> = State.Success(DoctorsViewState(emptyList()))
) {
    fun clear() {
        nextKey = ""
    }

    val doctorsState = state.data
}