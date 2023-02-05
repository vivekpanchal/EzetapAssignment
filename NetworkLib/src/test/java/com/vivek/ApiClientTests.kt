package com.vivek

import com.vivek.networklib.NetworkImpl
import com.vivek.networklib.NetworkService
import com.vivek.networklib.Networking
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ApiClientTests {

    @Test
    fun sampleTest() {
        Assert.assertEquals(true, true)
    }

    @Test
    fun `GET fetch Custom UI`() = run {
        println("********************Executing Test Case*********************\n\n")
        val api = NetworkImpl()
        val response = api.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
        Assert.assertNotNull(response)
        //Assert.assertEquals(200, response.code())
        println("Result ->> ${response.toString()}")
    }


    @Test
    fun `GET Image URl`() =  run{
        println("********************Executing Test Case*********************\n\n")
        val api = NetworkImpl()
        val response = api.fetchLogo("https://demo.ezetap.com/portal/images/logo.gif")
       // Assert.assertEquals(200, response.code())
        response.forEach {
            println("Result ->> ${it}")
        }


    }

}