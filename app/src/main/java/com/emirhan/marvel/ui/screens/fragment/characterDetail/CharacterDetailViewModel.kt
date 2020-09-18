package com.emirhan.marvel.ui.screens.fragment.characterDetail

import androidx.lifecycle.MutableLiveData
import com.emirhan.marvel.base.BaseViewModel
import com.emirhan.marvel.data.local.LocalStorage
import com.emirhan.marvel.data.remote.ComicsSeriesEventsItem
import com.emirhan.marvel.data.remote.MarvelCharacter
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : BaseViewModel() {

    val filteredComicList = MutableLiveData<List<ComicsSeriesEventsItem>>()
    val operationResult = MutableLiveData<Boolean>()
    val isContainsCharacter = MutableLiveData<Boolean>()

    fun addToFavourite(character: MarvelCharacter) {
        operationResult.value = localStorage.addCharacterToFavouriteList(character)
    }

    fun removeFromFavourites(character: MarvelCharacter) {
        operationResult.value = localStorage.removeCharacterFromFavouriteList(character)
    }

    fun isContainsCharacter(character: MarvelCharacter) {
        isContainsCharacter.value = localStorage.getFavouriteCharacterList().contains(character)
    }

    fun getFilteredComicList(comicList: List<ComicsSeriesEventsItem>) {
        var newComicList = comicList.filter { item ->
            val name = item.name
            val yearIndex = name?.indexOf("(")
            if (name?.substring(yearIndex!! + 1, yearIndex + 2)?.toIntOrNull() != null) {
                val yearString = name.substring(yearIndex!! + 1, yearIndex + 5)
                yearString.toInt() > 2005
            } else false
        }.sortedByDescending { item ->
            val name = item.name
            val yearIndex = name?.indexOf("(")
            val yearString = name?.substring(yearIndex!! + 1, yearIndex + 5)
            yearString?.toInt()
        }

        if (newComicList.size > 10) {
            newComicList = newComicList.subList(0, 9)
        }

        filteredComicList.value = newComicList
    }

}