package com.emirhan.marvel.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryItem(
    val resourceURI: String?,
    val name: String?,
    val type: String?
) : Parcelable