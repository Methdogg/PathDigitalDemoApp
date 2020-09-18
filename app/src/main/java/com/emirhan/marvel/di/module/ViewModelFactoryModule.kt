package com.emirhan.marvel.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emirhan.marvel.di.scope.ApplicationScope
import com.emirhan.marvel.ui.screens.fragment.characterDetail.CharacterDetailViewModel
import com.emirhan.marvel.ui.screens.fragment.characterList.CharacterListViewModel
import com.emirhan.marvel.ui.screens.fragment.favouriteCharacters.FavouriteCharactersViewModel
import com.emirhan.marvel.util.ViewModelFactory
import com.emirhan.marvel.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ApplicationModule::class])
abstract class ViewModelFactoryModule {
    @ApplicationScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCharacterListViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteCharactersViewModel::class)
    abstract fun bindFavouriteCharactersViewModel(viewModel: FavouriteCharactersViewModel): ViewModel
}