package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class WindX(
    @SerializedName("Direction")
    val direction: DirectionXX,
    @SerializedName("Speed")
    val speed: SpeedXX
)