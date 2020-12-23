package com.ezafebrian.favorite.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezafebrian.core.ui.FeedsAdapter
import com.ezafebrian.favorite.R
import com.ezafebrian.favorite.databinding.FragmentFavoriteBinding
import com.ezafebrian.favorite.module.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val viewModel:FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        loadKoinModules(favoriteModule)

        binding?.apply {
            val adapter = FeedsAdapter {
                val directions = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(it)
                findNavController().navigate(directions)
            }

            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            viewModel.likedPost.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                textEmpty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}