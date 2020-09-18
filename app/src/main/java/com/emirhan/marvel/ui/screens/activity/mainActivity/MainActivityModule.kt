package com.emirhan.marvel.ui.screens.activity.mainActivity

import com.emirhan.marvel.base.BaseActivity
import com.emirhan.marvel.di.module.ActivityModule
import com.emirhan.marvel.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {
    @ActivityScope
    @Binds
    abstract fun bindBaseActivity(activity: MainActivity): BaseActivity
}