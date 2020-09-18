package com.emirhan.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emirhan.marvel.util.DisposableContainer
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var disposableContainer: DisposableContainer

    open val isBackButtonEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            isBackButtonEnabled
        )

        if (savedInstanceState == null)
            initViews()
    }

    override fun onDestroy() {
        disposableContainer.dispose()
        super.onDestroy()
    }

    protected fun addFragment(fragment: Fragment, isAddToPopBackStack: Boolean) {
        (requireActivity() as BaseActivity).addFragment(fragment, isAddToPopBackStack)
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()
}