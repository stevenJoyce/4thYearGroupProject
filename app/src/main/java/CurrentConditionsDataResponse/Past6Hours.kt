package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past6Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXX
)