package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Ceiling(
    @SerializedName("Imperial")
    val imperial: ImperialX,
    @SerializedName("Metric")
    val metric: MetricX
)