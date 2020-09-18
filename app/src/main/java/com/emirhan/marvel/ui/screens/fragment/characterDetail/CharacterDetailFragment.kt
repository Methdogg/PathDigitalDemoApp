package com.emirhan.marvel.ui.screens.fragment.characterDetail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.emirhan.marvel.R
import com.emirhan.marvel.base.BaseMVVMFragment
import com.emirhan.marvel.data.remote.ComicsSeriesEventsItem
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.extension.load
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : BaseMVVMFragment<CharacterDetailViewModel>() {

    override val isBackButtonEnabled = true

    private val marvelCharacter: MarvelCharacter? by lazy {
        arguments?.getParcelable(CHARACTER)
    }

    override fun getLayoutId(): Int = R.layout.fragment_character_detail

    override fun initViews() {
        marvelCharacter?.let { character ->
            imageViewCharacter.load("${character.thumbnail?.path}/standard_medium.${character.thumbnail?.extension}")
            textViewCharacterName.text = character.name

            val comicList = character.comics?.items
            comicList?.let { list ->
                viewModel.getFilteredComicList(list)
            }

            viewModel.isContainsCharacter(character)
        }
    }

    override fun initObservers() {
        val listObserver = Observer<List<ComicsSeriesEventsItem>> { list ->
            recyclerViewComics.adapter = ComicAdapter(list)
        }

        val operationResultObserver = Observer<Boolean> { isAdded ->
            if (isAdded)
                Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_SHORT)
                    .show()
            else
                Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_SHORT)
                    .show()

            marvelCharacter?.let { character ->
                viewModel.isContainsCharacter(character)
            }
        }

        val containOperationResultObserver = Observer<Boolean> { isContains ->
            marvelCharacter?.let { character ->
                if (isContains) {
                    buttonAddToFavourites.text = getString(R.string.remove_from_favourites)
                    buttonAddToFavourites.setOnClickListener {
                        viewModel.removeFromFavourites(character)
                    }
                } else {
                    buttonAddToFavourites.text = getString(R.string.add_to_favourites)
                    buttonAddToFavourites.setOnClickListener {
                        viewModel.addToFavourite(character)
                    }
                }
            }
        }

        viewModel.filteredComicList.observe(this, listObserver)
        viewModel.operationResult.observe(this, operationResultObserver)
        viewModel.isContainsCharacter.observe(this, containOperationResultObserver)
    }

    companion object {
        const val CHARACTER = "CHARACTER"

        fun newInstance(character: MarvelCharacter): CharacterDetailFragment =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER, character)
                }
            }
    }
}