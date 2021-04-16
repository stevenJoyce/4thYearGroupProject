package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class WindChillTemperature(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXXXXXXXXXX
)