package HourlyDataResponse


import com.google.gson.annotations.SerializedName

data class WindGust(
    @SerializedName("Speed")
    val speed: SpeedX
)