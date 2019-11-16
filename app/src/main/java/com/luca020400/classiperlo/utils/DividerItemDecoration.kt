package com.luca020400.classiperlo.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context, private val orientation: Orientation) :
    RecyclerView.ItemDecoration() {
    private val mDivider: Drawable

    enum class Orientation {
        Horizontal,
        Vertical
    }

    init {
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        val drawable = a.getDrawable(0)
        require(drawable != null) { "invalid drawable" }
        mDivider = drawable
        a.recycle()
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        for (child in parent.children) {
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (child in parent.children) {
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) =
        when (orientation) {
            Orientation.Horizontal -> drawHorizontal(c, parent)
            Orientation.Vertical -> drawVertical(c, parent)
        }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) = when (orientation) {
        Orientation.Horizontal -> outRect.set(0, 0, mDivider.intrinsicWidth, 0)
        Orientation.Vertical -> outRect.set(0, 0, 0, mDivider.intrinsicHeight)
    }
}