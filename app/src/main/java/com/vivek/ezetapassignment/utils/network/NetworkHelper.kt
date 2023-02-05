package com.vivek.ezetapassignment.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Vivek Panchal on 4/02/23
 * https://vivekpanchal.dev
 * Copyright (c) 4/02/23 . All rights reserved.
 */

@Singleton
class NetworkHelper @Inject constructor(@ApplicationContext private val context: Context) {



     fun isNetworkConnected(): Boolean {
        var result = false
         val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val networkCapabilities = connectivityManager.activeNetwork ?: return false
         val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
         result = when {
             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
             activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)->true
             else -> false
         }
         return result
    }




     fun castToNetworkError(throwable: Throwable): NetworkError {
        val defaultNetworkError = NetworkError()
        try {
            Timber.e(throwable.localizedMessage)
            when (throwable) {
                is ConnectException -> {
                    return NetworkError(0, "0")
                }
                is HttpException -> {
                    val error = GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()
                        .fromJson(
                            throwable.response()?.errorBody()?.string(),
                            NetworkError::class.java
                        )
                    Timber.e("error=>  %s", error.toString())
                    return NetworkError(throwable.code(), error.statusCode, error.message)
                }
                else -> {
                    return defaultNetworkError
                }
            }
        } catch (e: IOException) {
            Timber.e(e.toString())
        } catch (e: JsonSyntaxException) {
            Timber.e(e.toString())
        } catch (e: NullPointerException) {
            Timber.e(e.toString())
        }
        return defaultNetworkError
    }
}