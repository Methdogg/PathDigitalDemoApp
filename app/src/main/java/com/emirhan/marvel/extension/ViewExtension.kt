package com.emirhan.marvel.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirhan.marvel.util.view.SimpleDivider

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun AppCompatImageView.load(url: String) {
    Glide.with(context).load(url)
        .into(this)
}

fun RecyclerView.setDivider(
    @DrawableRes drawableRes: Int? = null,
    @ColorRes colorRes: Int? = null,
    height: Int? = null,
    padding: Int = 0,
    orientation: SimpleDivider.Orientation = SimpleDivider.Orientation.VERTICAL,
    showLastDivider: Boolean = true
) {
    this.addItemDecoration(
        SimpleDivider(
            context = context,
            drawableRes = drawableRes,
            colorRes = colorRes,
            height = height?.px,
            padding = padding,
            orientation = orientation,
            showLastDivider = showLastDivider
        )
    )
}