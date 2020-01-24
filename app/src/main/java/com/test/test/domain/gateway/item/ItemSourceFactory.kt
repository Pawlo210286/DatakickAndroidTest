package com.test.test.domain.gateway.item

import androidx.paging.DataSource
import com.test.test.domain.data.Item

class ItemSourceFactory(
    private val gateway: IItemGateway
) : DataSource.Factory<Int, Item>() {

    var query: String = ""

    override fun create(): DataSource<Int, Item> {
        return ItemSource(gateway, query)
    }
}