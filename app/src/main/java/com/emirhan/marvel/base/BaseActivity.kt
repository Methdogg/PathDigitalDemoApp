package com.emirhan.marvel.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.emirhan.marvel.R
import com.emirhan.marvel.util.DisposableContainer
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var activity: Activity

    @Inject
    lateinit var disposableContainer: DisposableContainer

    @Inject
    lateinit var fragmentManager: FragmentManager

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        if (savedInstanceState == null)
            initViews()
    }

    override fun onDestroy() {
        disposableContainer.dispose()
        super.onDestroy()
    }

    fun showDialog() {
        if (dialog == null)
            dialog = Dialog(activity)
        dialog?.setContentView(R.layout.dialog_progress)
        dialog?.setCancelable(false)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.show()
    }

    fun dismissDialog() {
        dialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }

    fun addFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (isAddToBackStack)
            transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()
}