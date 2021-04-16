package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past3Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXX
)