package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class Speed(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)