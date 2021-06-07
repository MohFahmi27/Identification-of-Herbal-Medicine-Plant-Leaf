package com.mfahmi.mymedicineplantidentification.ui.bookmark

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.FragmentBookmarkBinding
import com.mfahmi.mymedicineplantidentification.ui.adapter.BookmarkAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {
    private val binding: FragmentBookmarkBinding by viewBinding()
    private val viewModel: BookmarkViewModel by viewModel()
    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plantsBookmark.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.lytEmptyPlaceholder.lytEmptyLinear.setVisibility(true)
                binding.rvBookmarkCapture.setVisibility(false)
            } else {
                binding.lytEmptyPlaceholder.lytEmptyLinear.setVisibility(false)
                bookmarkAdapter.setData(it)
                binding.rvBookmarkCapture.setVisibility(true)
            }
        }

        bookmarkAdapter.onItemClick = { plantDomain ->
            val action = BookmarkFragmentDirections.bookmarkFragmentToDetailFragment(plantDomain)
            findNavController().navigate(action)
        }

        with(binding.rvBookmarkCapture) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = bookmarkAdapter
        }
    }

    private fun View.setVisibility(state: Boolean) {
        if (state) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }
}