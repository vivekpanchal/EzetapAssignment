package com.vivek.networklib.response

import com.google.gson.annotations.SerializedName


data class UiResponse(
    @SerializedName("heading-text")
    val headingText: String,
    @SerializedName( "logo-url")
    val logoUrl: String,
    @SerializedName( "uidata")
    val uidata: List<Uidata>
)

data class Uidata(
    @SerializedName( "hint")
    val hint: String? = null,
    @SerializedName( "key")
    val key: String?= null,
    @SerializedName( "uitype")
    val uitype: String?= null,
    @SerializedName( "value")
    val value: String?=null
)