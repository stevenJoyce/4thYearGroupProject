package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class DirectionX(
    @SerializedName("Degrees")
    val degrees: Int,
    @SerializedName("English")
    val english: String,
    @SerializedName("Localized")
    val localized: String
)