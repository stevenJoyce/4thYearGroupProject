package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class WeeklyProcessedData(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>,
    @SerializedName("Headline")
    val headline: Headline
)