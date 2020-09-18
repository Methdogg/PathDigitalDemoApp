package com.emirhan.marvel.di.module

import com.emirhan.marvel.di.scope.ChildFragmentScope
import com.emirhan.marvel.di.scope.FragmentScope
import com.emirhan.marvel.ui.screens.fragment.characterDetail.CharacterDetailFragment
import com.emirhan.marvel.ui.screens.fragment.characterList.CharacterListFragment
import com.emirhan.marvel.ui.screens.fragment.favouriteCharacters.FavouriteCharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindCharacterListFragment(): CharacterListFragment

    @ChildFragmentScope
    @ContributesAndroidInjector
    abstract fun bindCharacterDetailFragment(): CharacterDetailFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFavouriteCharactersFragment(): FavouriteCharactersFragment
}