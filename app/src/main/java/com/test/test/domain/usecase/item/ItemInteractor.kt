package com.test.test.domain.usecase.item

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.test.domain.data.Item
import com.test.test.domain.gateway.item.IItemGateway
import com.test.test.domain.gateway.item.ItemSourceFactory
import java.util.concurrent.Executors

class ItemInteractor(
    private val gateway: IItemGateway,
    private val factory: ItemSourceFactory,
    private val config: PagedList.Config
) : ItemUseCase {

    override fun getItems(query: String): LiveData<PagedList<Item>> {
        factory.query = query

        return LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}