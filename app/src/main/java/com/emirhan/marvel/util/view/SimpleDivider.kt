package com.emirhan.marvel.util.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class SimpleDivider(
    context: Context,
    @DrawableRes drawableRes: Int?,
    @ColorRes colorRes: Int?,
    var height: Int?,
    var padding: Int = 0,
    var orientation: Orientation = Orientation.VERTICAL,
    var showLastDivider: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? = when {
        drawableRes != null -> {
            ContextCompat.getDrawable(context, drawableRes)
        }
        colorRes != null -> {
            GradientDrawable().apply {
                setColor(ContextCompat.getColor(context, colorRes))
            }
        }
        else -> null
    }

    private val bounds = Rect()

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null || parent.layoutManager == null) {
            return
        }

        if (orientation == Orientation.VERTICAL) {
            drawVertical(canvas, parent, divider)
        } else {
            drawHorizontal(canvas, parent, divider)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView, divider: Drawable) {
        canvas.save()

        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            if (!showLastDivider && i == childCount - 1) {
                continue
            }

            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val bottom = bounds.bottom + child.translationY.roundToInt()
            val top = bottom - (height ?: divider.intrinsicHeight)

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }

        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView, divider: Drawable) {
        canvas.save()

        val top: Int
        val bottom: Int

        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft,
                top,
                parent.width - parent.paddingRight,
                bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            if (!showLastDivider && i == childCount - 1) {
                continue
            }

            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, bounds)
            val right = bounds.right + child.translationX.roundToInt()
            val left = right - (height ?: divider.intrinsicWidth)

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when {
            divider == null -> outRect.set(0, 0, 0, 0)
            orientation == Orientation.VERTICAL -> outRect.set(0, 0, 0, divider.intrinsicHeight)
            else -> outRect.set(0, 0, divider.intrinsicWidth, 0)
        }
    }

    enum class Orientation {
        HORIZONTAL,
        VERTICAL
    }
}