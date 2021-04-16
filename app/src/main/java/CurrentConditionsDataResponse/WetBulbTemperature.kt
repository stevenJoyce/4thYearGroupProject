package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class WetBulbTemperature(
    @SerializedName("Imperial")
    val imperial: ImperialXXXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("Metric")
    val metric: MetricXXXXXXXXXXXXXXXXXXXXXXXX
)