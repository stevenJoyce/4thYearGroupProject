package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class MaximumX(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXXX
)