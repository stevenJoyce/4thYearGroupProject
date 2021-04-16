package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class SpeedX(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXXXXXXXXXXX
)