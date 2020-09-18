package com.emirhan.marvel.ui.screens.fragment.characterList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.emirhan.marvel.base.BaseViewModel
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.network.service.MarvelService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterListViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var marvelService: MarvelService

    val moviesList = MutableLiveData<MutableList<MarvelCharacter>>()

    override fun onViewAttached() {
        addDisposable(getCharacters())
    }

    fun getCharacters(page: Int = 0): Disposable {
        loading.value = true
        return marvelService.getCharacters(offset = page * 30)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                loading.value = false
                if (response.code == 200 && response.status == "Ok") {
                    response.data?.let { data ->
                        if (data.results.isNullOrEmpty()) {
                            moviesList.value = ArrayList()
                        } else {
                            moviesList.value = data.results
                        }
                    }
                }
            }, { onError ->
                loading.value = false
                Log.e("OnError", onError.message ?: "Hata")
            })
    }
}