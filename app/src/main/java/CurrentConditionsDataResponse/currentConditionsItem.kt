package CurrentConditionsDataResponse


import com.google.gson.annotations.SerializedName

data class currentConditionsItem(
    @SerializedName("ApparentTemperature")
    val apparentTemperature: ApparentTemperature,
    @SerializedName("Ceiling")
    val ceiling: Ceiling,
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("DewPoint")
    val dewPoint: DewPoint,
    @SerializedName("EpochTime")
    val epochTime: Int,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,
    @SerializedName("IsDayTime")
    val isDayTime: Boolean,
    @SerializedName("Link")
    val link: String,
    @SerializedName("LocalObservationDateTime")
    val localObservationDateTime: String,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("ObstructionsToVisibility")
    val obstructionsToVisibility: String,
    @SerializedName("Past24HourTemperatureDeparture")
    val past24HourTemperatureDeparture: Past24HourTemperatureDeparture,
    @SerializedName("Precip1hr")
    val precip1hr: Precip1hr,
    @SerializedName("PrecipitationSummary")
    val precipitationSummary: PrecipitationSummary,
    @SerializedName("PrecipitationType")
    val precipitationType: Any,
    @SerializedName("Pressure")
    val pressure: Pressure,
    @SerializedName("PressureTendency")
    val pressureTendency: PressureTendency,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: RealFeelTemperatureShade,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("Temperature")
    val temperature: Temperature,
    @SerializedName("TemperatureSummary")
    val temperatureSummary: TemperatureSummary,
    @SerializedName("UVIndex")
    val uVIndex: Int,
    @SerializedName("UVIndexText")
    val uVIndexText: String,
    @SerializedName("Visibility")
    val visibility: Visibility,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("WeatherText")
    val weatherText: String,
    @SerializedName("WetBulbTemperature")
    val wetBulbTemperature: WetBulbTemperature,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindChillTemperature")
    val windChillTemperature: WindChillTemperature,
    @SerializedName("WindGust")
    val windGust: WindGust
)