package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class DegreeDaySummary(
    @SerializedName("Cooling")
    val cooling: Cooling,
    @SerializedName("Heating")
    val heating: Heating
)