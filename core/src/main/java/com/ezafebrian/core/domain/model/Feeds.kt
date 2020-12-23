package com.ezafebrian.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Feeds(
    var postId: String,
    var imageUrl: String? = null,
    var caption: String? = null,
    var tags: String? = null,
    var ownerProfilePicture: String? = null,
    var ownerName: String? = null,
    var isLiked: Boolean = false
) : Parcelable