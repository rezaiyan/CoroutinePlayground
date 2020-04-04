package ir.alirezaiyan.arzte.testdata

import android.os.AsyncTask
import ir.alirezaiyan.arzte.core.entity.Doctor
import kotlinx.coroutines.asCoroutineDispatcher


object TestData {
    val DOCTOR_1 = Doctor("1", "name1", "https://png.pngtree.com/png-clipart/20190516/original/pngtree-users-vector-icon-png-image_3725294.jpg", "Address")
    val DOCTOR_2 = Doctor("2", "name2", "https://png.pngtree.com/png-clipart/20190516/original/pngtree-users-vector-icon-png-image_3725294.jpg", "Address")
    val DOCTOR_3 = Doctor("3", "name3", "https://png.pngtree.com/png-clipart/20190516/original/pngtree-users-vector-icon-png-image_3725294.jpg", "Address")
    val DOCTOR_4 = Doctor("4", "name4", "https://png.pngtree.com/png-clipart/20190516/original/pngtree-users-vector-icon-png-image_3725294.jpg", "Address")

//    val TEST_DISPATCHER = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()

}
