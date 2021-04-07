package WeeklyDataResponse


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("HoursOfIce")
    val hoursOfIce: Double,
    @SerializedName("HoursOfPrecipitation")
    val hoursOfPrecipitation: Double,
    @SerializedName("HoursOfRain")
    val hoursOfRain: Double,
    @SerializedName("HoursOfSnow")
    val hoursOfSnow: Double,
    @SerializedName("Ice")
    val ice: Ice,
    @SerializedName("IceProbability")
    val iceProbability: Int,
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("LongPhrase")
    val longPhrase: String,
    @SerializedName("PrecipitationIntensity")
    val precipitationIntensity: String,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,
    @SerializedName("PrecipitationType")
    val precipitationType: String,
    @SerializedName("Rain")
    val rain: Rain,
    @SerializedName("RainProbability")
    val rainProbability: Int,
    @SerializedName("ShortPhrase")
    val shortPhrase: String,
    @SerializedName("Snow")
    val snow: Snow,
    @SerializedName("SnowProbability")
    val snowProbability: Int,
    @SerializedName("ThunderstormProbability")
    val thunderstormProbability: Int,
    @SerializedName("TotalLiquid")
    val totalLiquid: TotalLiquid,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindGust")
    val windGust: WindGust
)