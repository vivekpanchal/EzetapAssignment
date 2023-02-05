package com.vivek.ezetapassignment.utils

import com.vivek.networklib.ApiError
import com.vivek.networklib.ApiException
import com.vivek.networklib.ApiResult
import com.vivek.networklib.ApiSuccess

class CommonExtensions {
}




/**
 * these extension functions are used to handle API results
 */
suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiSuccess<T>) {
        executable(data)
    }
}


suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ApiResult<T> = apply {
    if (this is ApiException<T>) {
        executable(e)
    }
}