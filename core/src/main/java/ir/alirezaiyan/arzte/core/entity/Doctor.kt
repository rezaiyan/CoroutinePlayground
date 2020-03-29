package ir.alirezaiyan.arzte.core.entity

import com.google.gson.Gson
import kotlin.math.round

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/5/2020 1:51 PM.
 */

data class DoctorResponse(var lastKey: String?, var doctors: List<Doctor>)

data class Doctor(
    var id: String? = null,
    var name: String? = null,
    var photoId: String? = null,
    var address: String? = null,
    var reviewCount: Int? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var website: String? = null
) {

    var rating: Float = 0F
        get() = round(field)

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Doctor) {
            return false
        }
        return other.id.equals(id)
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + id.hashCode()
        return hash
    }

    override fun toString(): String {
        return Gson().toJson(this@Doctor)
    }
}

private fun Gson.toJson(doctor: Doctor): String {
    return toJson(doctor)
}

class DoctorRateComparator : Comparator<Doctor> {
    override fun compare(o1: Doctor?, o2: Doctor?): Int {
        return ((o2!!.rating!! - o1!!.rating!!).toInt())
    }
}
fun String.toDoctor() = Gson().fromJson(this, Doctor::class.java)

private fun Gson.fromJson(s: String, java: Class<Doctor>): Doctor {
    return Gson().fromJson(s, java)
}
