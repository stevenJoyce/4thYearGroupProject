package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class ApparentTemperature(
    @SerializedName("Imperial")
    val imperial: Imperial,
    @SerializedName("Metric")
    val metric: Metric
)