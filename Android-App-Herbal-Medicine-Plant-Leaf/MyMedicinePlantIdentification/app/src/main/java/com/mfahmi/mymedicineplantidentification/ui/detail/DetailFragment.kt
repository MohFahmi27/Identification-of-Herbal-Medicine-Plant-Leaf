package com.mfahmi.mymedicineplantidentification.ui.detail

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvTitleDetail.text = args.herbalPlantData.title
            tvDateDetail.text = args.herbalPlantData.date
            imgPlantDetail.setImageBitmap(args.herbalPlantData.img)
            tvDescriptionPlantDetail.text = args.herbalPlantData.description
        }
    }

}