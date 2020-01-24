package com.test.test.source.remote.common

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.test.test.source.remote.data.common.result.ErrorResult
import com.test.test.source.remote.data.common.result.SuccessResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class BaseRepositoryTest {

    lateinit var repository: BaseRepository
    lateinit var api: TestApi

    enum class Result {
        SUCCESS, CONNECTION_ERROR, UN_AUTHORIZE, EMPTY
    }

    private fun setUp(result: Result) {
        repository = TestRepository()

        val response: Response<String> = when (result) {
            Result.SUCCESS -> {
                mock {
                    on { isSuccessful } doReturn true
                    on { body() } doReturn "1"
                    on { code() } doReturn 200
                }
            }
            Result.UN_AUTHORIZE -> {
                mock {
                    on { isSuccessful } doReturn false
                    on { body() } doReturn ""
                    on { code() } doReturn 401
                    on { message() } doReturn "Unauthorized"
                }
            }
            Result.CONNECTION_ERROR -> {
                mock {
                    on { isSuccessful } doReturn false
                    on { body() } doReturn ""
                    on { code() } doReturn 0
                    on { message() } doReturn "Connection error"
                }
            }
            Result.EMPTY -> {
                mock {
                    on { isSuccessful } doReturn true
                    on { body() } doReturn null
                    on { code() } doReturn 200
                }
            }
        }

        api = when (result) {
            Result.CONNECTION_ERROR -> {
                mock {
                    on { call() } doAnswer {
                        throw Exception("connection error")
                    }
                }
            }
            Result.UN_AUTHORIZE,
            Result.EMPTY,
            Result.SUCCESS -> {
                mock {
                    on { call() } doReturn response.toDeferredAsync()
                }
            }
        }
    }

    @Test
    fun success() {
        setUp(Result.SUCCESS)

        runBlocking {
            val result = repository.execute {
                api.call()
            }

            require(result is SuccessResult)
            Assert.assertEquals("1", result.data)
        }
    }

    @Test
    fun connectionError() {
        setUp(Result.CONNECTION_ERROR)

        runBlocking {
            val result = repository.execute {
                api.call()
            }

            require(result is ErrorResult)
            Assert.assertEquals("connection error", result.error.message)
        }
    }

    @Test
    fun networkError() {
        setUp(Result.UN_AUTHORIZE)

        runBlocking {
            val result = repository.execute {
                api.call()
            }

            require(result is ErrorResult)
            require(result.error is IllegalArgumentException)
        }
    }

    @Test
    fun emptyError() {
        setUp(Result.EMPTY)

        runBlocking {
            val result = repository.execute {
                api.call()
            }

            require(result is ErrorResult)
            require(result.error is NullPointerException)
        }
    }


    private fun <T> T.toDeferredAsync() = GlobalScope.async { this@toDeferredAsync }

}