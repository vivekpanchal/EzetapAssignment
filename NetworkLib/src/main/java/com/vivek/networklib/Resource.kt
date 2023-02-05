package com.vivek.networklib

// A generic class that contains data and status about loading this data.

/**
 * reference
 * https://developer.android.com/jetpack/guide#addendum
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String?=null,data: T? = null) : Resource<T>(data, message)
}