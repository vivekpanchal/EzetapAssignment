package com.vivek.networklib


import org.json.JSONObject


interface NetworkService {

    fun fetchCustomUI(URL: String): JSONObject

    fun fetchLogo(URL: String): ByteArray

}
