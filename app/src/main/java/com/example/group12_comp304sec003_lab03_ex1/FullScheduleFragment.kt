
package com.example.group12_comp304sec003_lab03_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab03_ex1.databinding.FullScheduleFragmentBinding
import com.example.group12_comp304sec003_lab03_ex1.viewmodels.AirlineScheduleViewModel
import com.example.group12_comp304sec003_lab03_ex1.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class FullScheduleFragment: Fragment() {

    private var _binding: FullScheduleFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: AirlineScheduleViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FullScheduleFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineAdapter = AirlineAdapter({
            val action = FullScheduleFragmentDirections
                .actionFullScheduleFragmentToStopScheduleFragment(it.airlineName)

            view.findNavController().navigate(action)
        })
        recyclerView.adapter = airlineAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                airlineAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}