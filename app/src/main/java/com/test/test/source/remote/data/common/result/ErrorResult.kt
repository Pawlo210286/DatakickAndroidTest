package com.test.test.source.remote.data.common.result

class ErrorResult<T>(
    val error: Throwable
) : Result<T>()