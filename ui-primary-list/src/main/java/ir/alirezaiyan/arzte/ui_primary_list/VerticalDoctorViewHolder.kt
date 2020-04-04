package ir.alirezaiyan.arzte.ui_primary_list

import android.view.ViewGroup
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_primary_list.databinding.VerticalDoctorItemBinding
import ir.alirezaiyan.arzte.ui_sdk.DataBoundViewHolder

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/30/2020 2:15 AM.
 */
class VerticalDoctorViewHolder(parent: ViewGroup, viewModel: PrimaryListViewModel) :
    DataBoundViewHolder<Doctor, VerticalDoctorItemBinding>(parent, VerticalDoctorItemBinding::inflate) {
    init {
        binding.root.setOnClickListener {
            viewModel.openDoctorDetail(item)
        }
    }

    override fun bind(t: Doctor) {
        binding.doctor = t
    }
}