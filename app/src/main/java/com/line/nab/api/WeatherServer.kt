package com.line.nab.api

import androidx.lifecycle.LiveData
import com.line.nab.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServer {
    @GET("data/2.5/forecast/daily")
    fun getWeather(
        @Query("q") nameOfCity: String,
        @Query("cnt") numberForecastDays: String,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): LiveData<ApiResponse<WeatherResponse>>
}