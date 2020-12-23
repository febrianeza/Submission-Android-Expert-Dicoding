package com.ezafebrian.submissionexpert.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.ezafebrian.core.domain.model.Feeds
import com.ezafebrian.submissionexpert.R
import com.ezafebrian.submissionexpert.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs? by navArgs()

    private var likedState = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

        binding?.apply {
            args?.let { it ->
                val feeds = it.feeds
                ownerPhoto.load(feeds.ownerProfilePicture) {
                    crossfade(true)
                    placeholder(com.ezafebrian.core.R.drawable.owner_placeholder)
                    transformations(CircleCropTransformation())
                }

                ownerPhoto.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("feedsapp://owner")))
                }

                ownerName.text = feeds.ownerName
                contentImage.load(feeds.imageUrl) {
                    crossfade(true)
                    placeholder(com.ezafebrian.core.R.drawable.content_image_placeholder)
                }

                contentCaption.text = feeds.caption
                contentTag.text = feeds.tags

                likedState = feeds.isLiked
                setStatusLiked(likedState)
                buttonFavorite.setOnClickListener {
                    setLiked(feeds)
                }
            }
        }
    }

    private fun setLiked(feeds: Feeds) {
        likedState = !likedState
        viewModel.setLikeFeeds(feeds, likedState)
        setStatusLiked(likedState)
    }

    private fun setStatusLiked(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding?.buttonFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding?.buttonFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}