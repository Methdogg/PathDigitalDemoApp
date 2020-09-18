package com.emirhan.marvel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.emirhan.marvel.util.DisposableContainer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var disposableContainer: DisposableContainer

    internal val loading = MutableLiveData<Boolean>()
    lateinit var loadingObserver: Observer<Boolean>

    override fun onCleared() {
        super.onCleared()
        disposableContainer.dispose()
    }

    internal fun addDisposable(disposable: Disposable) {
        disposableContainer.addDisposable(disposable)
    }

    open fun onViewAttached() = Unit

    open fun onViewDetached() = Unit

    /*internal fun <T> simpleResponseCallback(
        showLoading: Boolean = true,
        onStart: (() -> Unit)? = null,
        onResponse: (T) -> Unit,
        onError: ((String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ): ResponseCallback<T> =
        ResponseCallback(
            onStart = {
                if (onStart != null)
                    onStart()
                else {
                    if (showLoading)
                        loading.value = true
                }
            },
            onResponse = {
                if (showLoading) {
                    loading.value = false
                }
                onResponse(it)
            },
            onError = {
                if (onError != null)
                    onError(it)
            },
            onComplete = {
                if (onComplete != null) {
                    onComplete()
                } else {
                    if (showLoading)
                        loading.value = false
                }
            }
        )*/
}