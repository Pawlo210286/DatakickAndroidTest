package com.test.test.source.remote.common

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface TestApi {
    fun call(): Deferred<Response<String>>
}