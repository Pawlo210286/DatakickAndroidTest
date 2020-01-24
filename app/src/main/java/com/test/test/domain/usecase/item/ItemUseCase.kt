package com.test.test.domain.usecase.item

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.test.test.domain.data.Item

interface ItemUseCase {
    fun getItems(query: String): LiveData<PagedList<Item>>
}