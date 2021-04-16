package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class Past24HourTemperatureDeparture(
    @SerializedName("Imperial")
    val imperial: ImperialXXX,
    @SerializedName("Metric")
    val metric: MetricXXX
)