package com.emirhan.marvel.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
    @SerializedName("available") val availableStoriesNumber: Int?,
    val items: List<StoryItem>?,
    @SerializedName("returned") val returnedStoriesNumber: Int?
) : Parcelable