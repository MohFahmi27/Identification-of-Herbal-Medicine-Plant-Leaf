package com.mfahmi.mymedicineplantidentification.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.databinding.FragmentHomeBinding
import com.mfahmi.mymedicineplantidentification.ui.adapter.PlantsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pgbHome.setVisibility(true)
        with(binding.rvPlantsPict) {
            layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
            viewModel.plant.observe(viewLifecycleOwner) {
                adapter = when(it) {
                    is ApiResponse.Error -> {
                        null
                    }
                    is  ApiResponse.Success -> {
                        PlantsAdapter(it.data)
                    }
                    is ApiResponse.Empty -> {
                        null
                    }
                }
                binding.pgbHome.setVisibility(false)
            }
        }
    }

    private fun ProgressBar.setVisibility(state: Boolean) {
        if (state) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }
}