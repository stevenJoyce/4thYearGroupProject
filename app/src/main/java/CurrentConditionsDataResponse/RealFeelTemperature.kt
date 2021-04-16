package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class RealFeelTemperature(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXX
)