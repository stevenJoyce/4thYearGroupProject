package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Maximum(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXX
)