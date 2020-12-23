package com.ezafebrian.core.utils

import com.ezafebrian.core.data.source.local.entity.FeedsEntity
import com.ezafebrian.core.data.source.remote.response.PostsResponse
import com.ezafebrian.core.domain.model.Feeds

object DataMapper {
    fun mapResponseToEntities(input: PostsResponse): List<FeedsEntity> {
        val arrayListEntity = ArrayList<FeedsEntity>()
        input.data?.map {
            it?.let {
                arrayListEntity.add(
                    FeedsEntity(
                        postId = it.postId,
                        imageUrl = it.image,
                        caption = it.text,
                        tags = it.tags?.joinToString(prefix = "#", separator = ", #"),
                        ownerProfilePicture = it.owner.picture,
                        ownerName = "${it.owner.firstName} ${it.owner.lastName}"
                    )
                )
            }
        }
        return arrayListEntity
    }

    fun mapEntitiesToDomain(input: List<FeedsEntity>): List<Feeds> =
        input.map {
            Feeds(
                postId = it.postId,
                imageUrl = it.imageUrl,
                caption = it.caption,
                tags = it.tags,
                ownerProfilePicture = it.ownerProfilePicture,
                ownerName = it.ownerName,
                isLiked = it.isLiked
            )
        }

    fun mapDomainToEntity(input: Feeds): FeedsEntity = FeedsEntity(
        postId = input.postId,
        imageUrl = input.imageUrl,
        caption = input.caption,
        tags = input.tags,
        ownerProfilePicture = input.ownerProfilePicture,
        ownerName = input.ownerName,
        isLiked = input.isLiked
    )
}