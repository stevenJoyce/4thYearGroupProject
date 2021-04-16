package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class PressureTendency(
    @SerializedName("Code")
    val code: String,
    @SerializedName("LocalizedText")
    val localizedText: String
)