package com.emirhan.marvel.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emirhan.marvel.util.ViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseMVVMFragment<VM : BaseViewModel> : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = provideViewModel()

        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingObserver = Observer { isLoading ->
            if (isLoading) {
                (requireActivity() as BaseActivity).showDialog()
            } else {
                (requireActivity() as BaseActivity).dismissDialog()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner, viewModel.loadingObserver)

        initObservers()
        viewModel.onViewAttached()
    }

    override fun onDestroyView() {
        viewModel.onViewDetached()

        super.onDestroyView()
    }

    protected open fun provideViewModel(): VM =
        ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

    @Suppress("UNCHECKED_CAST")
    fun <VM : BaseViewModel> getViewModelClass(): Class<VM> =
        (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments.first() as Class<VM>

    protected abstract fun initObservers()
}