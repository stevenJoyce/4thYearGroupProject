package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Precipitation(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXX
)