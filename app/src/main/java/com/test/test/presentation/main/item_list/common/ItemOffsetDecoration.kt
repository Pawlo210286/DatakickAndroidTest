package com.test.test.presentation.main.item_list.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView


class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(
        context.resources.getDimensionPixelSize(
            itemOffsetId
        )
    )

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)

        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        val itemCount = state.itemCount

        when {
            itemPosition == 0 -> {
                outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset / 2)
            }
            itemCount > 0 && itemPosition == itemCount - 1 -> {
                outRect.set(mItemOffset, mItemOffset / 2, mItemOffset, mItemOffset)
            }
            else -> {
                outRect.set(mItemOffset, mItemOffset / 2, mItemOffset, mItemOffset / 2)
            }
        }
    }
}