package com.ezafebrian.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ezafebrian.core.R
import com.ezafebrian.core.databinding.PostRecyclerItemBinding
import com.ezafebrian.core.domain.model.Feeds

class FeedsAdapter(var onClick: ((Feeds) -> Unit)) : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {

    private var data = ArrayList<Feeds>()

    fun submitList(data: List<Feeds>?) {
        if (data.isNullOrEmpty()) return
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PostRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feeds: Feeds) {
            binding.apply {
                feeds.let { feeds ->
                    ownerPhoto.load(feeds.ownerProfilePicture) {
                        crossfade(true)
                        placeholder(R.drawable.owner_placeholder)
                        transformations(CircleCropTransformation())
                    }

                    ownerName.text = feeds.ownerName
                    contentImage.load(feeds.imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.content_image_placeholder)
                    }

                    contentCaption.text = feeds.caption
                    contentTag.text = feeds.tags
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onClick.invoke(data[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsAdapter.ViewHolder =
        ViewHolder(
            PostRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FeedsAdapter.ViewHolder, position: Int) {
        holder.bind(this.data[position])
    }

    override fun getItemCount(): Int = this.data.size
}