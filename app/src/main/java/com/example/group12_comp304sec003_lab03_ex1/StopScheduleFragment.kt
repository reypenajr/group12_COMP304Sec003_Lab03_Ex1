package com.example.group12_comp304sec003_lab03_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab03_ex1.databinding.StopScheduleFragmentBinding
import com.example.group12_comp304sec003_lab03_ex1.viewmodels.AirlineScheduleViewModel
import com.example.group12_comp304sec003_lab03_ex1.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class StopScheduleFragment: Fragment() {

    companion object {
        var STOP_NAME = "airlineName"
    }

    private var _binding: StopScheduleFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airlineName: String

    private val viewModel: AirlineScheduleViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StopScheduleFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineAdapter = AirlineAdapter(showStatus = true,{})
        // by passing in the stop name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = airlineAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForStopName(airlineName).collect() {
                airlineAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
