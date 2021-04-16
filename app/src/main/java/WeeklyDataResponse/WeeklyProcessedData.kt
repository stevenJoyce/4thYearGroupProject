package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class WeeklyProcessedData(
    @SerializedName("DailyForecasts")
    val dailyForecasts: DailyForecast,
    @SerializedName("Headline")
    val headline: Headline
)