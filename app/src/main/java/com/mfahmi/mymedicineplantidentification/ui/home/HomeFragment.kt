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
import com.mfahmi.mymedicineplantidentification.ui.adapter.ProductsAdapter
import com.shashank.sony.fancytoastlib.FancyToast
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
                when (it) {
                    is ApiResponse.Error -> {
                        adapter = null
                        FancyToast.makeText(
                            requireContext(),
                            getString(R.string.msg_error),
                            FancyToast.LENGTH_SHORT,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                    is ApiResponse.Success -> {
                        adapter = PlantsAdapter(it.data)
                    }
                    is ApiResponse.Empty -> {
                        adapter = null
                        FancyToast.makeText(
                            requireContext(),
                            getString(R.string.msg_error),
                            FancyToast.LENGTH_SHORT,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                }
                binding.pgbHome.setVisibility(false)
            }
        }
        populateProducts()
    }

    private fun populateProducts() {
        with(binding.rvProductsHome) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            viewModel.plantsDummy.observe(viewLifecycleOwner) {
                adapter = ProductsAdapter(it)
            }
        }
    }

    private fun ProgressBar.setVisibility(state: Boolean) {
        if (state) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }
}