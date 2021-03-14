package com.example.sportsadvisor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.Request;
import okhttp3.Response
import java.io.IOException
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.create


open class ApiCall  {

    /*    fun fetchJson(){
        println("Attempting to Fetch JSON")

        //val url = "https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>";
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
            }
        })
    }*/

    fun getData(url: String): String {
        //val url = "api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={77ca5e86f12971fe4c62a84327927e4c}"
        
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).execute().use {
                response -> return response.body!!.string()
        }

    }
    private val JSON = "application/json; charset=utf-8".toMediaType()
    private val client = OkHttpClient()

    fun sendData(url: String, json: String ): String {
        val body: RequestBody = create(JSON, json)
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }


}