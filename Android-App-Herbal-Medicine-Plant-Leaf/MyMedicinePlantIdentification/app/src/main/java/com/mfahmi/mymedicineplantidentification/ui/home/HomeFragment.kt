package com.mfahmi.mymedicineplantidentification.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.FragmentHomeBinding
import com.mfahmi.mymedicineplantidentification.ui.adapter.PlantsAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvPlants) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            viewModel.plantsDummy.observe(viewLifecycleOwner) {
                adapter = PlantsAdapter(it)
            }
        }
    }
}