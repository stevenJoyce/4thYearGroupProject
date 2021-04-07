package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShade(
    @SerializedName("Maximum")
    val maximum: MaximumX,
    @SerializedName("Minimum")
    val minimum: MinimumX
)