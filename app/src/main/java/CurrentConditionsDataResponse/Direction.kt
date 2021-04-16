package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Direction(
    @SerializedName("Degrees")
    val degrees: Int,
    @SerializedName("English")
    val english: String,
    @SerializedName("Localized")
    val localized: String
)