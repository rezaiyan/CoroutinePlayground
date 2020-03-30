package ir.alirezaiyan.arzte.ui_primary_doctor_list;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_primary_doctor_list.databinding.PrimaryFragmentBinding
import ir.alirezaiyan.arzte.ui_sdk.BaseFragment
import ir.alirezaiyan.arzte.ui_sdk.DataBoundListAdapter
import ir.alirezaiyan.arzte.ui_sdk.uiSdkComponent
import ir.alirezaiyan.arzte.ui_sdk.utils.ErrorSignal
import ir.alirezaiyan.arzte.ui_sdk.utils.NavigationSignal
import ir.alirezaiyan.arzte.ui_sdk.utils.viewModel

class PrimaryListFragment : BaseFragment() {

    override fun layoutId() = R.layout.primary_fragment

    private val navigationController by lazy {
        requireActivity().application.uiSdkComponent.navigationController
    }

    private val viewModel by viewModel {
        primaryListFragmentComponent.viewModel
    }

    private lateinit var binding : PrimaryFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = PrimaryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadDoctors()
        val adapter = DataBoundListAdapter { VerticalDoctorViewHolder(it, viewModel) }
        binding.primaryList.adapter = adapter

        viewModel.state.observe(this) {
            binding.state = it
            it.state.data?.let {
                adapter.update(it.list)  }
            binding.executePendingBindings()
        }

        viewModel.state.observeSignals(this) {
            when (it) {
                is ErrorSignal -> navigationController.showError(requireActivity(), it.message)
                is NavigationSignal<*> -> navigationController.navigateDetail(this, it.params as Doctor)
            }
        }

        binding.viewModel = viewModel

    }

}