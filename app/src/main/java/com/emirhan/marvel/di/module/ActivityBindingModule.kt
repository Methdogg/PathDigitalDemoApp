package com.emirhan.marvel.di.module

import com.emirhan.marvel.di.scope.ActivityScope
import com.emirhan.marvel.ui.screens.activity.mainActivity.MainActivity
import com.emirhan.marvel.ui.screens.activity.mainActivity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}