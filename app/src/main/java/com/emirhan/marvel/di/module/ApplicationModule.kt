package com.emirhan.marvel.di.module

import android.content.Context
import com.emirhan.marvel.base.BaseApplication
import com.emirhan.marvel.data.local.LocalStorage
import com.emirhan.marvel.di.scope.ApplicationScope
import com.emirhan.marvel.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @ApplicationContext
    @ApplicationScope
    @Provides
    internal fun provideContext(application: BaseApplication): Context = application

    @ApplicationScope
    @Provides
    fun provideLocalStorage(
        @ApplicationContext context: Context
    ): LocalStorage = LocalStorage(context)
}