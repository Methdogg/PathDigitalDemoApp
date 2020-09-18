package com.emirhan.marvel.data.local

import android.content.Context
import androidx.preference.PreferenceManager
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.util.GsonHolder

class LocalStorage(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun addCharacterToFavouriteList(character: MarvelCharacter): Boolean {
        val cacheList = getFavouriteCharacterList()
        cacheList.add(character)
        val cacheListJson = GsonHolder.toJson(cacheList)

        val editor = preferences.edit()
        editor.putString(FAVOURITE_LIST, cacheListJson)
        return editor.commit()
    }

    fun removeCharacterFromFavouriteList(character: MarvelCharacter): Boolean {
        val cacheList = getFavouriteCharacterList()
        cacheList.remove(character)
        val cacheListJson = GsonHolder.toJson(cacheList)

        val editor = preferences.edit()
        editor.putString(FAVOURITE_LIST, cacheListJson)
        return editor.commit()
    }

    fun getFavouriteCharacterList(): MutableList<MarvelCharacter> {
        val cacheList = preferences.getString(FAVOURITE_LIST, "")
        return GsonHolder.fromJson(cacheList) ?: ArrayList()
    }

    companion object {
        private const val FAVOURITE_LIST = "FAVOURITE_LIST"
    }
}