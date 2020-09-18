package com.emirhan.marvel.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: Int?,
    val name: String?,
    val description: String?,
    @SerializedName("modified") val modifiedDate: String?,
    val thumbnail: Thumbnail?,
    val resourceURI: String?,
    val comics: Comics?,
    val series: Series?,
    val stories: Stories?,
    val events: Events?,
    val urls: List<Url>?
) : Parcelable