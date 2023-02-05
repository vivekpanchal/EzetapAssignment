package com.vivek.ezetapassignment.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.vivek.networklib.ApiException
import com.vivek.networklib.ApiResult
import com.vivek.networklib.ApiSuccess
import com.vivek.networklib.NetworkService
import com.vivek.networklib.response.UiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class RepositoryIml @Inject constructor(
    val dispacher: CoroutineDispatcher,
    val api:NetworkService
) :Repository {

    override suspend fun fetchUi(): ApiResult<UiResponse> {
        return withContext(dispacher) {
            try {
                val res = api.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
                val jsonString = res.toString()
                val result=Gson().fromJson(jsonString, UiResponse::class.java)
                Timber.d("result: $result")
                ApiSuccess(result)
            } catch (e: Throwable) {
                Timber.e("Error: ${e.localizedMessage?.toString()}")
                ApiException(e)
            }
        }
    }

    override suspend fun fetchLogo(url: String): ApiResult<Bitmap> {
        return withContext(dispacher) {
            try {
                val bitmapArray = api.fetchLogo(url)
                val bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
                Timber.d("Logo: ${bitmap.byteCount}")
                ApiSuccess(bitmap)
            } catch (e: Throwable) {
                Timber.e("Error: ${e.localizedMessage?.toString()}")
                ApiException(e)
            }
        }
    }
}