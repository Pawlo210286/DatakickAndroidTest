package com.test.test.source.remote.api

import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi {

    @GET(ITEMS)
    fun getItemsAsync(
        @Query("page")
        page: Int
    ): Deferred<Response<List<LinkedTreeMap<Any, Any>>>>

    @GET(ITEMS)
    fun findItemsAsync(
        @Query("page")
        page: Int,
        @Query("query")
        query: String
    ): Deferred<Response<List<LinkedTreeMap<Any, Any>>>>

    companion object {
        private const val ITEMS = "items"
    }

}