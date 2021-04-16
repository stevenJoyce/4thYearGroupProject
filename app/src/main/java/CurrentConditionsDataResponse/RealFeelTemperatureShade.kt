package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShade(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXX
)