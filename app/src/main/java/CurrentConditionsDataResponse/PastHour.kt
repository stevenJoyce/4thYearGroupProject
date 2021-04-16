package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class PastHour(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXX
)