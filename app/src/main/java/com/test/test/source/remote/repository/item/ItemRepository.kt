package com.test.test.source.remote.repository.item

import com.google.gson.internal.LinkedTreeMap
import com.test.test.source.remote.api.ItemApi
import com.test.test.source.remote.common.BaseRepository
import com.test.test.source.remote.data.common.result.Result

class ItemRepository(
    private val api: ItemApi
) : BaseRepository(), IItemRepository {

    override suspend fun findItems(
        page: Int,
        query: String
    ): Result<List<LinkedTreeMap<Any, Any>>> {
        return execute {
            api.findItemsAsync(page, query)
        }
    }

    override suspend fun getItems(page: Int): Result<List<LinkedTreeMap<Any, Any>>> {
        return execute {
            api.getItemsAsync(page)
        }
    }
}