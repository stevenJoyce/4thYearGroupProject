package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class MetricXXXXXXXXXXXXXXXXXXXXX(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)