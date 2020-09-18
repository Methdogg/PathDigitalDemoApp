package com.emirhan.marvel.ui.screens.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.emirhan.marvel.R

class ProgressDialog : DialogFragment() {

    private val style = STYLE_NORMAL
    private val themeRes = R.style.Theme_AppCompat_Light_Dialog
    private val dimAmount = 0.60f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
        setStyle(style, themeRes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_progress, container, false)
        dialog?.setCanceledOnTouchOutside(true)
        return rootView
    }

    override fun onStart() {
        dialog?.window?.attributes = dialog?.window?.attributes?.apply {
            dimAmount = this@ProgressDialog.dimAmount
            flags = flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        }
        super.onStart()
    }
}