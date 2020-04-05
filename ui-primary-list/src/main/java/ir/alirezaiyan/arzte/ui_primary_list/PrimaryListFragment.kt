package ir.alirezaiyan.arzte.ui_primary_list;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.ui_primary_list.databinding.PrimaryFragmentBinding
import ir.alirezaiyan.arzte.core.utils.ErrorSignal
import ir.alirezaiyan.arzte.core.utils.NavigationSignal
import ir.alirezaiyan.arzte.ui_primary_list.databinding.VerticalDoctorItemBinding
import ir.alirezaiyan.arzte.ui_sdk.*
import ir.alirezaiyan.arzte.ui_sdk.utils.StateLayout
import ir.alirezaiyan.arzte.ui_sdk.utils.viewModel
import kotlinx.android.synthetic.main.primary_fragment.*

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

    private val endlessOnScrollListener = object : EndlessOnScrollListener() {
        override fun onLoadMore() {
            viewModel.loadMoreDoctors()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadDoctors()
        val dataBoundListAdapter = DataBoundListAdapter { VerticalDoctorViewHolder(it, viewModel) }

        val listComponent = ListComponent
            .Builder<Doctor, VerticalDoctorItemBinding>(requireContext()).apply {
                title = getString(R.string.primaryListTitle)
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                adapter = dataBoundListAdapter
                scrollListener = endlessOnScrollListener
            }.build()
        primaryContainer.addView(listComponent)

        view?.findViewById<StateLayout<DoctorsViewState>>(R.id.stateLayout)
            ?.setUpdateListener {
            dataBoundListAdapter.update(it.list)
        }

        viewModel.state.observe(this) {
            binding.response = it
            binding.executePendingBindings()
        }

        viewModel.state.observeSignals(this) {
            when (it) {
                is ErrorSignal -> {
                    navigationController.showError(requireActivity(), it.message)
                    endlessOnScrollListener.onFailure()
                }
                is NavigationSignal<*> -> navigationController.navigateDetail(this, it.params as Doctor)
            }
        }

        binding.viewModel = viewModel

    }

}