package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Pressure(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXX
)