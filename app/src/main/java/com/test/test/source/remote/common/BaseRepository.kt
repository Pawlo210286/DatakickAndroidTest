package com.test.test.source.remote.common

import androidx.annotation.VisibleForTesting
import com.test.test.source.remote.data.common.result.ErrorResult
import com.test.test.source.remote.data.common.result.Result
import com.test.test.source.remote.data.common.result.SuccessResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.HttpURLConnection

abstract class BaseRepository {
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    suspend fun <T> execute(block: suspend () -> Deferred<Response<T>>): Result<T> {
        return try {
            val data = block().await()
            if (data.code() != HttpURLConnection.HTTP_OK) {
                ErrorResult(IllegalArgumentException("${data.code()}: ${data.errorBody()?.string()}"))
            } else {
                val body = data.body()
                if (body == null) {
                    ErrorResult(NullPointerException("Response is empty"))
                } else {
                    SuccessResult(body)
                }
            }
        } catch (e: Exception) {
            ErrorResult(e)
        }
    }
}