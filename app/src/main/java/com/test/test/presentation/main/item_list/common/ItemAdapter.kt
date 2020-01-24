package com.test.test.presentation.main.item_list.common

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.test.test.domain.data.Item

class ItemAdapter(
    private val itemSelectListener: (Item) -> Unit
) : PagedListAdapter<Item, ItemHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent, itemSelectListener)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }
}