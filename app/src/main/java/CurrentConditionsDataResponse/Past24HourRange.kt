package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past24HourRange(
    @SerializedName("Maximum")
    val maximum: MaximumX,
    @SerializedName("Minimum")
    val minimum: MinimumX
)