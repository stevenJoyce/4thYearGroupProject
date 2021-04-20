package com.example.sportsadvisor

import CurrentConditionsDataResponse.currentConditionsItem
import HourlyDataResponse.HourlyProcessedDataItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

object WeatherDataProcessor : AppCompatActivity() {

    var dataRetreive:String = ""
    val gson = Gson()
    var list: List<String> = ArrayList()

    fun fetchHourlyJson(url: String): String {
        println("Attempting to Fetch JSON")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        var body = ""
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                println("Fetched JSON Data")
                body = response.body?.string().toString()
                saveHourlyData(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
        return body
    }

    fun fetchCurrentJson(url: String): String {
        println("Attempting to Fetch JSON")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        var body = ""
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                println("Fetched JSON Data")
                body = response.body?.string().toString()
                saveCurrentData(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
        return body
    }

    fun saveHourlyData(body: String){
        dataRetreive = body
        println(dataRetreive)
        //gson object
        val commentResponse = gson.fromJson(body,Array<HourlyProcessedDataItem>::class.java)

        for (x in commentResponse.indices)
        {
            list = commentResponse[x].dateTime.split("T","+01:00")
                println(list[1])
                println(
                        "Rainfall: " + commentResponse[x].rain.value + " Wind Speed:  " + commentResponse[x].wind.speed.value +
                                " Temperature: " + commentResponse[x].temperature.value +
                                " Real Feel Temperature: " + commentResponse[x].realFeelTemperature.value +
                                " Humidity: " + commentResponse[x].realFeelTemperature.value +
                                " Daylight: " + commentResponse[x].isDaylight +
                                " Rating: " + UserResults.checkHourlyResults(
                                        commentResponse[x].rain.value,
                                        commentResponse[x].wind.speed.value,
                                        commentResponse[x].temperature.value,
                                        commentResponse[x].realFeelTemperature.value,
                                        commentResponse[x].relativeHumidity,
                                        commentResponse[x].isDaylight
                    )
                )
            
        }
    }

    fun saveCurrentData(body: String){
        dataRetreive = body
        println(dataRetreive)
        //gson object
        val commentResponse: List<currentConditionsItem> = gson.fromJson(body,Array<currentConditionsItem>::class.java).toList()

        for (x in commentResponse.indices)
        {
                println(
                    UserResults.checkHourlyResults(
                        commentResponse[x].precip1hr.metric.value,
                        commentResponse[x].wind.speed.metric.value,
                        commentResponse[x].temperature.metric.value,
                        commentResponse[x].realFeelTemperature.metric.value,
                        commentResponse[x].relativeHumidity,
                        commentResponse[x].isDayTime
                    )
                )
        }
    }

   open fun callHourlyData(courseCode:String){
        val url = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/"+courseCode+"?apikey=Gngag9jfLyY2fDDrLSr27EVYD1TarOiW&language=en-us&details=true&metric=true"
        fetchHourlyJson(url)
    }

    open fun callCurrentData(courseCode:String){
        val url = "https://dataservice.accuweather.com/currentconditions/v1/"+courseCode+"?apikey=Gngag9jfLyY2fDDrLSr27EVYD1TarOiW&language=en-us&details=true"
        fetchCurrentJson(url)
    }

}

