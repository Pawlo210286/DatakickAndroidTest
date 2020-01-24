package com.test.test.source.remote.data.common.result

class SuccessResult<T>(
    val data: T
) : Result<T>()