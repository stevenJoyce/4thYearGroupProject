package com.example.sportsadvisor

import java.math.BigDecimal
import java.math.RoundingMode

object UserResults  {

    var result = 0.0;

    open fun checkHourlyResults(rainfall:Double, windSpeed:Double, temperature:Double, feelsLikeTemp:Double, humidity:Int): Double {
        val rainResult = checkRainfall(rainfall)
        val windResult = checkWindSpeed(windSpeed)
        val tempResult = checkTemp(temperature)
        val tempFeelResult = checkTempFeel(feelsLikeTemp)
        val humidityResult = checkHumidity(humidity)

        val TotalResultScore = humidityResult + windResult+ tempResult + tempFeelResult + rainResult

        return TotalResultScore
    }


    open fun checkWeeklyResults(rainfall:Double, windSpeed:Double, temperature:Double, feelsLikeTemp:Double, hoursOfPrec:Double): Double {
        val rainResult = checkRainfall(rainfall)
        val windResult = checkWindSpeed(windSpeed)
        val tempResult = checkTemp(temperature)
        val tempFeelResult = checkTempFeel(feelsLikeTemp)
        val precipitationResult = checkHoursOfPrec(hoursOfPrec)

        val TotalResultScore = precipitationResult + windResult+ tempResult + tempFeelResult + rainResult

        return TotalResultScore
    }

    private fun checkHoursOfPrec(hoursOfPrec: Double): Double {
        result = 0.0;
        if (hoursOfPrec in 0.0..2.0)
        {
            result = 1.0;
        }
        else
        {
            when
            {
                ((hoursOfPrec >10.0)) ->{
                    result = 0.0
                }
                ((hoursOfPrec > 2.0) and (hoursOfPrec <= 4.0))  ->{
                    result = 0.8
                }
                ((hoursOfPrec > 4.0) and (hoursOfPrec <= 6.0)) -> {
                    result = 0.6
                }
                ((hoursOfPrec > 6.0) and (hoursOfPrec <= 8.0)) -> {
                    result = 0.4
                }
                ((hoursOfPrec > 8.0) and (hoursOfPrec <= 10.0)) -> {
                    result = 0.2
                }
            }
        }

        return result
    }

    private fun checkHumidity(humidity: Int): Double {
        result = 0.0;
        if (humidity in 30..60)
        {
            result = 1.0
        }
        else
        {
            when
            {
                ((humidity <= 10) or (humidity >=90)) ->{
                    result = 0.3
                }
                ((humidity > 10) or(humidity <= 29))  ->{
                    result = 0.5
                }
                ((humidity > 60) or (humidity < 90)) -> {
                    result = 0.7
                }
            }
        }

        return result
    }

    private fun checkTempFeel(feelsLikeTemp: Double): Double {

        result = 0.0;

        var result = 0.0


        if (feelsLikeTemp in 10.0..20.0)
        {
            result = 1.0
        }
        else
        {
            when
            {
                ((feelsLikeTemp > 20.0) and (feelsLikeTemp <=25.0)) ->{
                    result = 0.8
                }
                ((feelsLikeTemp  >25.0) and (feelsLikeTemp <= 30.0))  ->{
                    result = 0.6
                }
                ((feelsLikeTemp > 5.0) and (feelsLikeTemp < 10.0)) -> {
                    result = 0.4
                }
                ((feelsLikeTemp > 30.0) and (feelsLikeTemp <= 35.0)) -> {
                    result = 0.2
                }
                ((feelsLikeTemp > 0.0) and (feelsLikeTemp <= 5.0)) -> {
                    result = 0.2
                }
                ((feelsLikeTemp > 35.0) or (feelsLikeTemp <= 0))  -> {
                    result = 0.0
                }
            }
        }
        return result
    }

    private fun checkTemp(temperature: Double): Double {

         result = 0.0;

        var result = 0.0


        if (temperature in 10.0..20.0)
        {
            result = 1.0
        }
        else
        {
            when
            {
                ((temperature > 20.0) and (temperature <=25.0)) ->{
                    result = 0.8
                }
                ((temperature  >25.0) and (temperature <= 30.0))  ->{
                    result = 0.6
                }
                ((temperature > 5.0) and (temperature < 10.0)) -> {
                    result = 0.4
                }
                ((temperature > 30.0) and (temperature <= 35.0)) -> {
                    result = 0.2
                }
                ((temperature > 0.0) and (temperature <= 5.0)) -> {
                    result = 0.2
                }
                ((temperature > 35.0) or (temperature <= 0))  -> {
                    result = 0.0
                }
            }
        }
        return result
    }

    private fun checkRainfall(rainfall: Double): Double {

         result = 0.0;

        var result = 0.0


        if (rainfall in 0.0..1.0)
        {
            result = 1.0
        }
        else
        {
            when
            {
                (rainfall < 2.0) ->{
                    result = 0.8
                }
                (rainfall < 3.0)  ->{
                    result = 0.6
                }
                (rainfall < 4.0) -> {
                    result = 0.4
                }
                (rainfall < 5.0) -> {
                    result = 0.2
                }
                (rainfall > 5.0)  -> {
                    result = 0.0
                }
            }
        }

        return result
    }


    private fun checkWindSpeed(windSpeed: Double): Double {

         result = 0.0;

        var result = 0.0


        if (windSpeed in 0.0..8.0)
        {
            result = 1.0
        }
        else
        {
            when
            {
                ((windSpeed >= 9.0) and (windSpeed <=16.0)) ->{
                    result = 0.8
                }
                ((windSpeed >= 17.0) and (windSpeed <= 24.0))  ->{
                    result = 0.6
                }
                ((windSpeed >= 25.0) and (windSpeed <= 32.0)) -> {
                    result = 0.4
                }
                ((windSpeed >= 33.0) and (windSpeed <= 40.0)) -> {
                    result = 0.2
                }
                (windSpeed >= 40.0)  -> {
                    result = 0.0
                }
            }
        }

        return result
    }
}
