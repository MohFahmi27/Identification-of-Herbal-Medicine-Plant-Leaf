package com.mfahmi.mymedicineplantidentification.ui.detail

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.FragmentDetailBinding
import com.mfahmi.mymedicineplantidentification.ui.adapter.ProductsAdapter

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvTitleDetail.text = args.herbalPlantData.title
            tvDateDetail.text = args.herbalPlantData.date
            imgPlantDetail.setImageBitmap(args.herbalPlantData.img)
            tvDescriptionPlantDetail.text = args.herbalPlantData.description
        }
        populateProducts()
    }

    private fun populateProducts() {
        with(binding.rvProductsDetail) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            viewModel.plantsDummy.observe(viewLifecycleOwner) {
                adapter = ProductsAdapter(it)
            }
        }
    }

}