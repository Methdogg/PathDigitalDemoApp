package com.emirhan.marvel.network.service

import com.emirhan.marvel.data.remote.MarvelCharacterResponse
import com.emirhan.marvel.util.network.ApiConstants
import com.emirhan.marvel.util.network.AuthItemsGenerator
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getCharacters(
        @Query("apikey") apiKey: String = ApiConstants.PUBLIC_API_KEY,
        @Query("hash") hash: String = AuthItemsGenerator.getMD5Key(),
        @Query("ts") timeStamp: String = AuthItemsGenerator.getTimeStamp(),
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): Single<MarvelCharacterResponse>
}