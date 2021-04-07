package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class RealFeelTemperature(
    @SerializedName("Maximum")
    val maximum: Maximum,
    @SerializedName("Minimum")
    val minimum: Minimum
)