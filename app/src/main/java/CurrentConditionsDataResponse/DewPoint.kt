package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class DewPoint(
    @SerializedName("Imperial")
    val imperial: ImperialXX,
    @SerializedName("Metric")
    val metric: MetricXX
)