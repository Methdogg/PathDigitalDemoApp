package com.emirhan.marvel.ui.screens.fragment.favouriteCharacters

import androidx.lifecycle.MutableLiveData
import com.emirhan.marvel.base.BaseViewModel
import com.emirhan.marvel.data.local.LocalStorage
import com.emirhan.marvel.data.remote.MarvelCharacter
import javax.inject.Inject

class FavouriteCharactersViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : BaseViewModel() {

    val favouriteCharacterList = MutableLiveData<MutableList<MarvelCharacter>>()

    override fun onViewAttached() {
        getFavouriteCharacterList()
    }

    private fun getFavouriteCharacterList() {
        val cacheList = localStorage.getFavouriteCharacterList()
        favouriteCharacterList.value = cacheList
    }
}