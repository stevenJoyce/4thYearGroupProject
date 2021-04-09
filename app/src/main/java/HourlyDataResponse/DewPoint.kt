package HourlyDataResponse


import com.google.gson.annotations.SerializedName

data class DewPoint(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)