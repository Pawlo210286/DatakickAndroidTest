package com.test.test.presentation.main.item_details.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.test.R
import com.test.test.domain.data.Item
import kotlinx.android.synthetic.main.list_item_item_detail.view.*

class ItemDetailHolder private constructor(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(detailKey: String, item: Item) {
        itemView.apply {
            tv_key.text = detailKey.replace("_", " ")
            tv_value.text = item.otherFields[detailKey]
        }
    }


    companion object {
        fun create(parent: ViewGroup): ItemDetailHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_item_detail, parent, false)
            return ItemDetailHolder(v)
        }
    }
}