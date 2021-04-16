package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXX
)