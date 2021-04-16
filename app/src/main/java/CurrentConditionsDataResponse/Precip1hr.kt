package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Precip1hr(
    @SerializedName("Imperial")
    val imperial: ImperialXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXX
)