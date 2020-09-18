package com.emirhan.marvel.di.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.emirhan.marvel.base.BaseActivity
import com.emirhan.marvel.di.scope.ActivityScope
import com.methdogg.lodosmovieapp.di.qualifier.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {
    @ActivityScope
    @Binds
    abstract fun bindAppcompatActivity(activity: BaseActivity): AppCompatActivity

    @ActivityScope
    @Binds
    abstract fun bindActivity(activity: AppCompatActivity): Activity

    @ActivityContext
    @ActivityScope
    @Binds
    abstract fun bindActivityContext(activity: Activity): Context

    companion object {
        @JvmStatic
        @ActivityScope
        @Provides
        fun provideFragmentManager(
            activity: AppCompatActivity
        ): FragmentManager = activity.supportFragmentManager
    }
}