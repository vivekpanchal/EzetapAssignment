package com.vivek.ezetapassignment.data

import android.graphics.Bitmap
import com.vivek.networklib.ApiResult
import com.vivek.networklib.response.UiResponse


interface Repository {

    suspend fun fetchUi(): ApiResult<UiResponse>

    suspend fun fetchLogo(url: String): ApiResult<Bitmap>
}