package com.vivek.networklib


import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.Response
import org.json.JSONObject

private const val TAG = "ApiResult"

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>


fun <T : Any> apiHandler(
    execute: () -> Response
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body
        println("apiHandler: ${body.toString()}")
        if (response.isSuccessful && body != null) {
            val jsonString = Gson().toJson(body)
            println("apiHandler: Json -> \n $jsonString")
            val json = JSONObject(jsonString)
            ApiSuccess(body as T)
        } else {
            println("apiHandler: Some API error code ${response.code}  message  : ${response.message}")
            ApiError(code = response.code, message = response.message)
        }
    } catch (e: Throwable) {
        println( "apiHandler: Exception occured ${e.localizedMessage}")
        ApiException(e)
    }
}
