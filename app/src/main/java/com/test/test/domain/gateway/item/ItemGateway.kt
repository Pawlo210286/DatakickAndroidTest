package com.test.test.domain.gateway.item

import com.test.test.domain.data.Item
import com.test.test.domain.data.mapper.toItem
import com.test.test.source.remote.data.common.result.SuccessResult
import com.test.test.source.remote.repository.item.IItemRepository

class ItemGateway(
    private val remote: IItemRepository
) : IItemGateway {

    override suspend fun findItems(page: Int, query: String): List<Item> {
        val result = remote.findItems(page, query)

        return if (result is SuccessResult) {
            result.data.map { it.toItem() }
        } else {
            emptyList()
        }
    }

    override suspend fun getItems(page: Int): List<Item> {
        val result = remote.getItems(page)

        return if (result is SuccessResult) {
            result.data.map { it.toItem() }
        } else {
            emptyList()
        }
    }
}