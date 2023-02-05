package com.vivek.networklib

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException


class NetworkImpl : NetworkService {

    private val client = OkHttpClient()

    override fun fetchCustomUI(URL: String): JSONObject {
        val request = Request.Builder().url(URL).build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        val json = response.body!!.string()
        return JSONObject(json)
    }

    override fun fetchLogo(URL: String): ByteArray {
        val request = Request.Builder().url(URL).build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        return response.body!!.bytes()
    }
}