package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past18Hours(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXX
)