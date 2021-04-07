package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Maximum")
    val maximum: MaximumXX,
    @SerializedName("Minimum")
    val minimum: MinimumXX
)