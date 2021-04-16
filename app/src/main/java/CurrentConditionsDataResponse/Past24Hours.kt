package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past24Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXX
)