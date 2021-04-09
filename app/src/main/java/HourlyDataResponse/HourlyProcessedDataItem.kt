package HourlyDataResponse


import com.google.gson.annotations.SerializedName

data class HourlyProcessedDataItem(
    @SerializedName("Ceiling")
    val ceiling: Ceiling,
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("DewPoint")
    val dewPoint: DewPoint,
    @SerializedName("EpochDateTime")
    val epochDateTime: Int,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("Ice")
    val ice: Ice,
    @SerializedName("IceProbability")
    val iceProbability: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,
    @SerializedName("IsDaylight")
    val isDaylight: Boolean,
    @SerializedName("Link")
    val link: String,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,
    @SerializedName("Rain")
    val rain: Rain,
    @SerializedName("RainProbability")
    val rainProbability: Int,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("Snow")
    val snow: Snow,
    @SerializedName("SnowProbability")
    val snowProbability: Int,
    @SerializedName("Temperature")
    val temperature: Temperature,
    @SerializedName("TotalLiquid")
    val totalLiquid: TotalLiquid,
    @SerializedName("UVIndex")
    val uVIndex: Int,
    @SerializedName("UVIndexText")
    val uVIndexText: String,
    @SerializedName("Visibility")
    val visibility: Visibility,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("WetBulbTemperature")
    val wetBulbTemperature: WetBulbTemperature,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindGust")
    val windGust: WindGust
)