package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Speed(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXXXXXXXXX
)