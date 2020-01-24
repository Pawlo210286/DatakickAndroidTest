package com.test.test.domain.gateway.item

import com.test.test.domain.data.Item

interface IItemGateway {
    suspend fun getItems(page: Int): List<Item>
    suspend fun findItems(page: Int, query: String): List<Item>
}