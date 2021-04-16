package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past6HourRange(
    @SerializedName("Maximum")
    val maximum: MaximumXX,
    @SerializedName("Minimum")
    val minimum: MinimumXX
)