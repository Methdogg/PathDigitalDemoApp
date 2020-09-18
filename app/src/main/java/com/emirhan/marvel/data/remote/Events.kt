package com.emirhan.marvel.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    @SerializedName("available") val availableEventsNumber: Int?,
    val collectionURI: String?,
    val items: List<ComicsSeriesEventsItem>?,
    @SerializedName("returned") val returnedEventsNumber: Int?
) : Parcelable