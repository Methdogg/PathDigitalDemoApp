package com.emirhan.marvel.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.emirhan.marvel.util.view.ViewModelFactory
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseMVVMActivity<VM : BaseViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        viewModel = provideViewModel()
        viewModel.onViewAttached()

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        viewModel.onViewDetached()
        super.onDestroy()
    }

    protected open fun provideViewModel(): VM =
        ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

    @Suppress("UNCHECKED_CAST")
    fun <VM : BaseViewModel> getViewModelClass(): Class<VM> =
        (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments.first() as Class<VM>
}