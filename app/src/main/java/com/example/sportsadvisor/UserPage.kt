package com.example.sportsadvisor

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.*
import java.io.IOException

class UserPage : AppCompatActivity() {

    var dataRetreive:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)

        val url = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/3528176?apikey=Gngag9jfLyY2fDDrLSr27EVYD1TarOiW&language=en-us&details=true&metric=true"
        fetchJson(url)


    }

        fun fetchJson(url: String): String {
            println("Attempting to Fetch JSON")
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            var body = ""
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    println("Fetched JSON Data")
                    body = response.body?.string().toString()
                    saveData(body)
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }
            })
            return body
        }

        fun saveData(body: String){
            dataRetreive = body
            println(dataRetreive)
        }

}

