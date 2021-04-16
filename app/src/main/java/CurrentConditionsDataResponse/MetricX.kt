package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class MetricX(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)