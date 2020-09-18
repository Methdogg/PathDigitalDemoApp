package com.emirhan.marvel.di.component

import com.emirhan.marvel.base.BaseApplication
import com.emirhan.marvel.di.module.*
import com.emirhan.marvel.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApplication): ApplicationComponent
    }
}