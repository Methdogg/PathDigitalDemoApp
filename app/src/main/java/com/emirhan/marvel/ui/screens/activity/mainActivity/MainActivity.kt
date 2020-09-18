package com.emirhan.marvel.ui.screens.activity.mainActivity

import androidx.core.view.children
import com.emirhan.marvel.R
import com.emirhan.marvel.base.BaseActivity
import com.emirhan.marvel.ui.screens.fragment.characterList.CharacterListFragment
import com.emirhan.marvel.ui.screens.fragment.favouriteCharacters.FavouriteCharactersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        setBottomNavigationMenu()
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0)
            fragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }

    private fun setBottomNavigationMenu() {
        val characterListFragment = CharacterListFragment()
        val favouriteCharactersFragment = FavouriteCharactersFragment()

        addFragment(characterListFragment, false)

        navigationLayout.children.forEachIndexed { index, view ->
            when (index) {
                0 -> {
                    view.setOnClickListener {
                        addFragment(characterListFragment, false)
                    }
                }
                1 -> {
                    view.setOnClickListener {
                        addFragment(favouriteCharactersFragment, false)
                    }
                }
            }
        }
    }

}