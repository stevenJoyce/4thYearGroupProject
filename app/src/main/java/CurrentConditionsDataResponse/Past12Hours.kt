package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past12Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXX
)