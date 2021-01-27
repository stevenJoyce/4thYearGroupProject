package com.example.sportsadvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this) // context, usually an Activity or Application
        //val longitude = 53.2707;
        // val latitude = 9.0568;
        // val result = URL("https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>").readText()
        // println(result)
        // Log.d("Result From API", result)
        fetchJson()

    }

    fun fetchJson(){
        println("Attempting to Fetch JSON")

        //val url = "https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>";
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build();
        val client = OkHttpClient();
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
            }

        })

    }
}