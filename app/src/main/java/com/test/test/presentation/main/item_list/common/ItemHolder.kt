package com.test.test.presentation.main.item_list.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.test.R
import com.test.test.domain.data.Item
import com.test.test.presentation.util.setupCarouselView
import kotlinx.android.synthetic.main.list_item_item_preview.view.*

class ItemHolder private constructor(
    v: View,
    private val selectListener: (Item) -> Unit
) : RecyclerView.ViewHolder(v) {

    fun bind(item: Item) {
        itemView.apply {
            tv_item_name.text = item.name
            tv_size.text = resources.getString(R.string.size, item.size)
            tv_serving_size.text =
                resources.getString(R.string.serving_size, item.serverSize)

            setOnClickListener {
                selectListener.invoke(item)
            }

            carousel_view?.setupCarouselView(
                images = item.images,
                onImageClick = {
                    selectListener.invoke(item)
                },
                withEmptyImageList = {
                    it.visibility = View.GONE
                })
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            selectListener: (Item) -> Unit
        ): ItemHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_item_preview, parent, false)
            return ItemHolder(v, selectListener)
        }
    }
}