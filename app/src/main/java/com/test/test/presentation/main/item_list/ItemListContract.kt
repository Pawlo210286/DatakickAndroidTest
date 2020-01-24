package com.test.test.presentation.main.item_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.test.test.domain.data.Item
import com.test.test.presentation.base.BaseViewModel

interface ItemListContract {
    interface ViewModel : BaseViewModel {
        val data: LiveData<PagedList<Item>>
        fun onItemSelected(item: Item)
        fun onQueryTextChange(query: String)
    }

    interface Router {
        fun navigateToDetailsFragment(item: Item)
    }
}