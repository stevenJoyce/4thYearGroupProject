package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Minimum(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXX
)