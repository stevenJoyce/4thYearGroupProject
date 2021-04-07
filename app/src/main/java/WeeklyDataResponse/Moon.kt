package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class Moon(
    @SerializedName("Age")
    val age: Int,
    @SerializedName("EpochRise")
    val epochRise: Int,
    @SerializedName("EpochSet")
    val epochSet: Int,
    @SerializedName("Phase")
    val phase: String,
    @SerializedName("Rise")
    val rise: String,
    @SerializedName("Set")
    val `set`: String
)