package com.test.test.source.remote.repository.item

import com.google.gson.internal.LinkedTreeMap
import com.test.test.source.remote.data.common.result.Result

interface IItemRepository {
    suspend fun getItems(page: Int): Result<List<LinkedTreeMap<Any, Any>>>
    suspend fun findItems(page: Int, query: String): Result<List<LinkedTreeMap<Any, Any>>>
}