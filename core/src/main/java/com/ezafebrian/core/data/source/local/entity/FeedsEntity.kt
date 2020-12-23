package com.ezafebrian.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "feeds_table")
class FeedsEntity(
    @PrimaryKey
    @NonNull
    var postId: String,
    var imageUrl: String? = null,
    var caption: String? = null,
    var tags: String? = null,
    var ownerProfilePicture: String?=null,
    var ownerName: String?=null,
    var isLiked: Boolean = false
) : Parcelable