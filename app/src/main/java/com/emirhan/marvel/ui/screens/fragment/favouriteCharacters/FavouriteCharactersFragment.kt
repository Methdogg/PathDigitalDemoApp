package com.emirhan.marvel.ui.screens.fragment.favouriteCharacters

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.emirhan.marvel.R
import com.emirhan.marvel.base.BaseMVVMFragment
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.extension.setDivider
import com.emirhan.marvel.extension.setVisible
import com.emirhan.marvel.ui.screens.fragment.characterList.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_favourite_characters.*

class FavouriteCharactersFragment : BaseMVVMFragment<FavouriteCharactersViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_favourite_characters

    private lateinit var characterAdapter: CharacterAdapter

    override fun initViews() {
        characterAdapter = CharacterAdapter()

        with(recyclerViewMovie) {
            adapter = characterAdapter
            setDivider(R.drawable.list_divider)
        }
    }

    override fun initObservers() {
        val listObserver: Observer<MutableList<MarvelCharacter>> = Observer {
            setData(it)
        }
        viewModel.favouriteCharacterList.observe(this, listObserver)
    }

    private fun setData(list: MutableList<MarvelCharacter>) {
        textViewEmptyList.setVisible(list.isEmpty())
        recyclerViewMovie.setVisible(list.isNotEmpty())

        if (recyclerViewMovie.isVisible) {
            characterAdapter.addItems(list)
        }
    }


}