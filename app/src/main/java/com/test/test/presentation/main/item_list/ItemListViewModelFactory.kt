package com.test.test.presentation.main.item_list

import com.test.test.domain.usecase.item.ItemUseCase
import com.test.test.presentation.base.BaseFactory

class ItemListViewModelFactory(
    private val router: ItemListContract.Router,
    private val itemUseCase: ItemUseCase
) : BaseFactory<ItemListViewModel>() {

    override fun createViewModel(): ItemListViewModel {
        return ItemListViewModel(router, itemUseCase)
    }
}