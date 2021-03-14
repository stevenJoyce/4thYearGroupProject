package com.example.sportsadvisor

class Algorithm {
    var sunrise:Double = 6.30
    var sunset:Double = 20.30
    var hrRating: Int = 0
    var rnRating: Int = 0
    var humRating: Int = 0
    var tempRating:Int = 0
    var windRating:Int = 0
    var starCount:Int = 0

    fun hourResult(time:Double) {
       if((time < sunrise) or ( time > sunset))
       {
            hrRating = 1
       }
        else
       {
           when {
               (time < 9.00) or (time < 18.00) -> {
                   hrRating = 2
               }
               (time < 11.00) or (time < 16.00) -> {
                   hrRating = 3
               }
               time < 13.00 -> {
                   hrRating = 4
               }
           }
       }
    }//hour result

    fun rainResult(rainInput:Double) {
        if (rainInput == 1.00)
        {
            rnRating = 1
        }
        else
        {
            when
            {
                rainInput < 0.80 ->{
                    rnRating = 2
                }
                rainInput < 0.60 ->{
                    rnRating = 3
                }
                rainInput < 0.40 ->{
                    rnRating = 3
                }
                rainInput < 0.20 ->{
                    rnRating = 4
                }
            }
        }
    }//funResult

    fun humidityResult(humidInput:Double){
        if (humidInput == 1.00)
        {
            humRating = 1
        }
        else
        {
            when
            {
                humidInput < 0.80 ->{
                    humRating = 2
                }
                humidInput < 0.60 ->{
                    humRating = 3
                }
                humidInput < 0.40 ->{
                    humRating = 3
                }
                humidInput < 0.20 ->{
                    humRating = 4
                }
            }
        }
    }

    fun windResult(windInput:Double){
        if (windInput == 1.00)
        {
            windRating = 1
        }
        else
        {
            when
            {
                windInput < 0.80 ->{
                    windRating = 2
                }
                windInput < 0.60 ->{
                    windRating = 3
                }
                windInput < 0.40 ->{
                    windRating = 3
                }
                windInput < 0.20 ->{
                    windRating = 4
                }
            }
        }
    }

    fun tempResult(tempInput:Double){
        if (tempInput == 0.00)
        {
            tempRating = 1
        }
        else
        {
            when
            {
                (tempInput < 10)  or (tempRating > 30) ->{
                    tempRating = 2
                }
                tempInput < 22 ->{
                    tempRating = 3
                }
                tempInput < 30 ->{
                    tempRating = 4
                }
            }
        }
    }

    fun ratingResult(){
        starCount = hrRating + rnRating + humRating + tempRating + windRating
        print(starCount)

    }








 

}