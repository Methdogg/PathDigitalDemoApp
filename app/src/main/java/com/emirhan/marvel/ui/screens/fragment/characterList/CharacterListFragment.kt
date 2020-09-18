package com.emirhan.marvel.ui.screens.fragment.characterList

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.marvel.R
import com.emirhan.marvel.base.BaseMVVMFragment
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.extension.setDivider
import com.emirhan.marvel.extension.setVisible
import com.emirhan.marvel.ui.screens.fragment.characterDetail.CharacterDetailFragment
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : BaseMVVMFragment<CharacterListViewModel>() {
    private lateinit var characterAdapter: CharacterAdapter

    private var offSet = 0

    override fun getLayoutId(): Int = R.layout.fragment_character_list

    override fun initViews() {
        offSet = 0

        characterAdapter = CharacterAdapter(onCharacterClickListener = { character ->
            addFragment(CharacterDetailFragment.newInstance(character), true)
        })

        with(recyclerViewMovie) {
            adapter = characterAdapter
            setDivider(R.drawable.list_divider)
        }

        recyclerViewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    offSet++

                    viewModel.getCharacters(page = offSet)
                }
            }
        })
    }

    override fun initObservers() {
        val listObserver: Observer<MutableList<MarvelCharacter>> = Observer {
            setData(it)
        }
        viewModel.moviesList.observe(this, listObserver)
    }

    private fun setData(list: MutableList<MarvelCharacter>) {
        textViewEmptyList.setVisible(list.isEmpty())
        recyclerViewMovie.setVisible(list.isNotEmpty())

        if (recyclerViewMovie.isVisible) {
            characterAdapter.addItems(list)
        }
    }
}