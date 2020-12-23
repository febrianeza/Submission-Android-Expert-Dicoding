package com.ezafebrian.submissionexpert.ui.feeds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezafebrian.core.data.Resource
import com.ezafebrian.core.ui.FeedsAdapter
import com.ezafebrian.submissionexpert.R
import com.ezafebrian.submissionexpert.databinding.FragmentFeedsBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FeedsFragment : Fragment(R.layout.fragment_feeds) {

    private var _binding: FragmentFeedsBinding? = null
    private val binding get() = _binding

    private val viewModel: FeedsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeedsBinding.bind(view)

        binding?.apply {
            val adapter = FeedsAdapter {
                val directions = FeedsFragmentDirections.actionFeedsFragmentToDetailFragment(it)
                findNavController().navigate(directions)
            }

            recyclerview.setHasFixedSize(true)
            recyclerview.adapter = adapter

            viewModel.feeds.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.submitList(resource.data)
                    }
                    is Resource.Loading -> progressBar.visibility = View.VISIBLE
                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.error_text),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        Log.d("FeedsFragment", "onViewCreated: ${resource.message}")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}