package com.test.test.presentation.main.item_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.test.test.domain.data.Item
import com.test.test.domain.usecase.item.ItemUseCase
import com.test.test.presentation.base.BaseViewModelImpl

class ItemListViewModel(
    private val router: ItemListContract.Router,
    private val itemUseCase: ItemUseCase
) : BaseViewModelImpl(), ItemListContract.ViewModel {

    private val searchQuery = MutableLiveData<String>()

    override val data: LiveData<PagedList<Item>>
        get() = searchQuery.switchMap {
            itemUseCase.getItems(it)
        }

    init {
        searchQuery.postValue("")
    }

    override fun onItemSelected(item: Item) {
        router.navigateToDetailsFragment(item)
    }

    override fun onQueryTextChange(query: String) {
        searchQuery.postValue(query)
    }
}