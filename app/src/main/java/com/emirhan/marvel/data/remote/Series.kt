package com.emirhan.marvel.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    @SerializedName("available") val availableSeriesNumber: Int?,
    val collectionURI: String?,
    val items: List<ComicsSeriesEventsItem>?,
    @SerializedName("returned") val returnedSeriesNumber: Int?
) : Parcelable