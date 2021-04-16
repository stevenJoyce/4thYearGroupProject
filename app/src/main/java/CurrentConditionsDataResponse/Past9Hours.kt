package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past9Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXX
)