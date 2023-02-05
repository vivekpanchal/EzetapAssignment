package com.vivek.ezetapassignment.utils.network




data class NetworkError(
    val status: Int = -1,

    val statusCode: String = "-1",

    val message: String = "Something went wrong",

)