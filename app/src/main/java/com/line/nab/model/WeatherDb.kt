package com.line.nab.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "weather", primaryKeys = ["city","date"])
data class WeatherDb(

    @ColumnInfo(name = "city")
    var name: String,

    @ColumnInfo(name = "date")
    var dt: Long,


    @ColumnInfo(name = "pressure")
    var pressure: Int?,

    @ColumnInfo(name = "humidity")
    var humidity: Int?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "temp_min")
    var tempMin: Double?,

    @ColumnInfo(name = "temp_max")
    var tempMax: Double?
) {
    companion object {
        fun parse(weatherRes: WeatherResponse, prop: Property): WeatherDb {
            return WeatherDb(
                name = weatherRes.city.name,
                description = prop.weather[0].description,
                dt = prop.dt.toLong(),
                humidity = prop.humidity,
                pressure = prop.pressure,
                tempMax = prop.temp.max,
                tempMin = prop.temp.min
            )
        }
    }
}