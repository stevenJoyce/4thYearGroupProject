package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("AirAndPollen")
    val airAndPollen: List<AirAndPollen>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Day")
    val day: Day,
    @SerializedName("DegreeDaySummary")
    val degreeDaySummary: DegreeDaySummary,
    @SerializedName("EpochDate")
    val epochDate: Int,
    @SerializedName("HoursOfSun")
    val hoursOfSun: Double,
    @SerializedName("Link")
    val link: String,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("Moon")
    val moon: Moon,
    @SerializedName("Night")
    val night: Night,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: RealFeelTemperatureShade,
    @SerializedName("Sources")
    val sources: List<String>,
    @SerializedName("Sun")
    val sun: Sun,
    @SerializedName("Temperature")
    val temperature: Temperature
)