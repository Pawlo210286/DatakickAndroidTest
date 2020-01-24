package com.test.test.domain.gateway.item

import androidx.paging.PageKeyedDataSource
import com.test.test.domain.data.Item
import kotlinx.coroutines.runBlocking

class ItemSource(
    private val gateway: IItemGateway,
    private val searchQuery: String
) : PageKeyedDataSource<Int, Item>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        runBlocking {
            val data = if (searchQuery.isEmpty()) {
                gateway.getItems(1)
            } else {
                gateway.findItems(1, searchQuery)
            }
            callback.onResult(data, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        runBlocking {
            val data = if (searchQuery.isEmpty()) {
                gateway.getItems(params.key)
            } else {
                gateway.findItems(params.key, searchQuery)
            }
            callback.onResult(data, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        runBlocking {
            val data = if (searchQuery.isEmpty()) {
                gateway.getItems(params.key)
            } else {
                gateway.findItems(params.key, searchQuery)
            }
            callback.onResult(data, params.key.dec())
        }
    }
}