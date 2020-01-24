package com.test.test.domain.gateway.item

import com.google.gson.internal.LinkedTreeMap
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.test.test.source.remote.data.common.result.ErrorResult
import com.test.test.source.remote.data.common.result.SuccessResult
import com.test.test.source.remote.repository.item.IItemRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ItemGatewayTest {

    private lateinit var gateway: ItemGateway

    enum class Result {
        SUCCESS, ERROR
    }

    private fun setUp(result: Result) {
        val repository: IItemRepository = when (result) {
            Result.SUCCESS -> {
                val item = mock<LinkedTreeMap<Any, Any>> { }

                mock {
                    onBlocking { getItems(any()) } doReturn SuccessResult(listOf(item, item, item))
                    onBlocking { findItems(any(), any()) } doReturn SuccessResult(listOf(item, item, item))
                }
            }
            Result.ERROR -> {
                mock {
                    onBlocking { getItems(any()) } doReturn ErrorResult(Exception())
                    onBlocking { findItems(any(), any()) } doReturn ErrorResult(Exception())
                }
            }
        }

        gateway = ItemGateway(repository)
    }

    @Test
    fun emptyResult() {
        setUp(Result.ERROR)
        runBlocking {
            val result = gateway.getItems(0)

            assert(result.isEmpty())
        }
    }

    @Test
    fun notEmptyResult() {
        setUp(Result.SUCCESS)
        runBlocking {
            val result = gateway.getItems(0)

            assert(result.isNotEmpty())
        }
    }

    @Test
    fun emptyFindResult() {
        setUp(Result.ERROR)
        runBlocking {
            val result = gateway.findItems(0, "query")

            assert(result.isEmpty())
        }
    }

    @Test
    fun notEmptyFindResult() {
        setUp(Result.SUCCESS)
        runBlocking {
            val result = gateway.findItems(0, "query")

            assert(result.isNotEmpty())
        }
    }
}