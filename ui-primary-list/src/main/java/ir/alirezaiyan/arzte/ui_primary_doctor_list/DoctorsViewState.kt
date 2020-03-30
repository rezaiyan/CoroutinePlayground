package ir.alirezaiyan.arzte.ui_primary_doctor_list

import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_sdk.utils.Lce

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/30/2020 12:47 AM.
 */
data class DoctorsViewState(
    val list: List<Doctor>,
    val nextKey: String? = null,
    val requestInvoked: Boolean = false,
    val loading : Boolean = false
){
    val emptyStateVisible: Boolean = requestInvoked && list.isEmpty()
}

data class PrimaryListState(
    var nextKey: String = "",
    val state: Lce<DoctorsViewState> = Lce.Success(DoctorsViewState(emptyList()))
) {
    fun clear() {
        nextKey = ""
    }

    val doctorsState = state.data
}