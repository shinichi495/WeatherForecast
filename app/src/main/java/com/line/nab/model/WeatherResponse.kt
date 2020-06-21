package com.line.nab.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city")
    @Expose
    val city: City,
    @SerializedName("cnt")
    @Expose
    val cnt: Int,
    @SerializedName("cod")
    @Expose
    val cod: String,
    @SerializedName("list")
    @Expose
    val list: List<Property>,
    @SerializedName("message")
    @Expose
    val message: Double
)

data class City(
    @SerializedName("coord")
    @Expose
    val coord: Coord,
    @SerializedName("country")
    @Expose
    val country: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("population")
    @Expose
    val population: Int,
    @SerializedName("timezone")
    @Expose
    val timezone: Int

)

data class Property(
    @SerializedName("clouds")
    @Expose
    val clouds: Int,
    @SerializedName("deg")
    @Expose
    val deg: Int,
    @SerializedName("dt")
    @Expose
    val dt: Int,
    @SerializedName("feels_like")
    @Expose
    val feels_like: FeelsLike,
    @SerializedName("humidity")
    @Expose
    val humidity: Int,
    @SerializedName("pressure")
    @Expose
    val pressure: Int,
    @SerializedName("rain")
    @Expose
    val rain: Double,
    @SerializedName("speed")
    @Expose
    val speed: Double,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int,
    @SerializedName("sunset")
    @Expose
    val sunset: Int,
    @SerializedName("temp")
    @Expose
    val temp: Temp,
    @SerializedName("weather")
    @Expose
    val weather: List<WeatherX>
)

data class Coord(
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lon")
    @Expose
    val lon: Double
)

@Entity()
data class FeelsLike(
    @SerializedName("day")
    @Expose
    val day: Double,
    @SerializedName("eve")
    @Expose
    val eve: Double,
    @SerializedName("morn")
    @Expose
    val morn: Double,
    @SerializedName("night")
    @Expose
    val night: Double
)

@Entity()
data class Temp(
    @SerializedName("day")
    @Expose
    val day: Double,
    @SerializedName("eve")
    @Expose
    val eve: Double,
    @SerializedName("max")
    @Expose
    val max: Double,
    @SerializedName("min")
    @Expose
    val min: Double,
    @SerializedName("morn")
    @Expose
    val morn: Double,
    @SerializedName("night")
    @Expose
    val night: Double
)

@Entity()
data class WeatherX(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("main")
    @Expose
    val main: String
)