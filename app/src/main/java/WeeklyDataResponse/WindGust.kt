package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class WindGust(
    @SerializedName("Direction")
    val direction: DirectionX,
    @SerializedName("Speed")
    val speed: SpeedX
)