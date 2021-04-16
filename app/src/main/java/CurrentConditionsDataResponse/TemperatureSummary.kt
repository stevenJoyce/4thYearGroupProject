package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class TemperatureSummary(
    @SerializedName("Past12HourRange")
    val past12HourRange: Past12HourRange,
    @SerializedName("Past24HourRange")
    val past24HourRange: Past24HourRange,
    @SerializedName("Past6HourRange")
    val past6HourRange: Past6HourRange
)