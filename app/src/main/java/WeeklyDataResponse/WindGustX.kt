package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class WindGustX(
    @SerializedName("Direction")
    val direction: DirectionXXX,
    @SerializedName("Speed")
    val speed: SpeedXXX
)