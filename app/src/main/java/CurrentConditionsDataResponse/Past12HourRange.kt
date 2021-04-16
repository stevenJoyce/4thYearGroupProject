package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past12HourRange(
    @SerializedName("Maximum")
    val maximum: Maximum,
    @SerializedName("Minimum")
    val minimum: Minimum
)