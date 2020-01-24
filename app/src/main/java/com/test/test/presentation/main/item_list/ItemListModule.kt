package com.test.test.presentation.main.item_list

import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object ItemListModule {

    fun get(fragment: ItemListFragment) = Kodein.Module("ItemListModule") {
        bind<ViewModelProvider.Factory>(tag = "ItemList") with singleton {
            ItemListViewModelFactory(instance(), instance())
        }
        bind<ItemListContract.ViewModel>() with provider {
            fragment.vm<ItemListViewModel>(instance(tag = "ItemList"))
        }
    }
}