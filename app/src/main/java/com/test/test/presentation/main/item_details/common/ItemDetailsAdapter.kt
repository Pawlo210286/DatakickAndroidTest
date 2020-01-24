package com.test.test.presentation.main.item_details.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.test.domain.data.Item

class ItemDetailsAdapter(val item: Item) : RecyclerView.Adapter<ItemDetailHolder>() {
    private val detailKeys = item.otherFields.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailHolder {
        return ItemDetailHolder.create(parent)
    }

    override fun getItemCount(): Int = detailKeys.size// + 1

    override fun onBindViewHolder(holder: ItemDetailHolder, position: Int) {
        holder.bind(detailKeys[position], item)
    }
}