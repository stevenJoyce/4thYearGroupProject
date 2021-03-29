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


open class UserResults  {

    var rainfall:Double = 0.0
    var windSpeed:Double = 22.2
    var temperature:Double = 14.1
    var visibilty:Double = 16.1
    var humidity:Int = 64

    open fun checkResults(rainfall:Double,windSpeed:Double,temperature:Double,visibilty:Double,humidity:Int): Double {
        val rainResult = checkRainfall(rainfall)
        val windResult = checkWindSpeed(windSpeed)
        val tempResult = checkTemp(temperature)
        val visibiltyResult = checkVisbility(visibilty)
        val humidityResult = checkHumidity(humidity)

        val TotalResultScore = humidityResult + windResult+ tempResult + visibiltyResult + rainResult

        return TotalResultScore
    }

    private fun checkHumidity(humidity: Int): Double {
        var result = 0.0;

        if (humidity in 30..60)
        {
            result = 1.0;
        }
        else
        {
            when
            {
                ((humidity <= 10) or (humidity >=90)) ->{
                    result = 0.2
                }
                ((humidity > 10) or(humidity <= 20))  ->{
                    result = 0.4
                }
                humidity < 0.40 ->{
                    result = 0.6
                }
                humidity > 90 ->{
                    result = 0.2
                }
            }
        }

        return result
    }

    private fun checkVisbility(visibilty: Double): Double {
        val result = 0.0;
        return result
    }

    private fun checkTemp(temperature: Double): Double {
        val result = 1.0;
        return result
    }

    private fun checkRainfall(rainfall: Double): Double {
        val result = 1.0;
        return result
    }

    private fun checkWindSpeed(windSpeed: Double): Double {

        val result = 0.0;
        return result
    }







}