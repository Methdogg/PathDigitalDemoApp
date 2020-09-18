package com.emirhan.marvel.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics(
    @SerializedName("available") val availableComicsNumber: Int?,
    val collectionURI: String?,
    val items: List<ComicsSeriesEventsItem>?,
    @SerializedName("returned") val returnedComicsNumber: Int?
) : Parcelable