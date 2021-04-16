package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class PrecipitationSummary(
    @SerializedName("Past12Hours")
    val past12Hours: Past12Hours,
    @SerializedName("Past18Hours")
    val past18Hours: Past18Hours,
    @SerializedName("Past24Hours")
    val past24Hours: Past24Hours,
    @SerializedName("Past3Hours")
    val past3Hours: Past3Hours,
    @SerializedName("Past6Hours")
    val past6Hours: Past6Hours,
    @SerializedName("Past9Hours")
    val past9Hours: Past9Hours,
    @SerializedName("PastHour")
    val pastHour: PastHour,
    @SerializedName("Precipitation")
    val precipitation: Precipitation
)