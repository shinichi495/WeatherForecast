package com.line.nab.repository

import androidx.lifecycle.LiveData
import com.line.nab.BuildConfig
import com.line.nab.api.ApiResponse
import com.line.nab.api.WeatherServer
import com.line.nab.db.WeatherDao
import com.line.nab.excutors.AppExcutors
import com.line.nab.model.Resource
import com.line.nab.model.WeatherDb
import com.line.nab.model.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val executor : AppExcutors,
    private val api : WeatherServer,
    private  val dao : WeatherDao
){

    fun loadWeatherByCity (nameOfCity : String) : LiveData<Resource<List<WeatherDb>>> {
        var city = nameOfCity
        return object : NetworkBoundResource<List<WeatherDb>,WeatherResponse>(executor) {
            override fun saveCallResult(item: WeatherResponse) {
                for (i in item.list ) {
                    val w = WeatherDb.parse(item,i)
                    dao.insert(w)
                }
                city = item.city.name
            }

            override fun loadFromDb(): LiveData<List<WeatherDb>> {
                return dao.findByCity(city)
            }


            override fun createCall(): LiveData<ApiResponse<WeatherResponse>> {
                return api.getWeather(nameOfCity,"7", BuildConfig.API_ID, "metric")
            }

            override fun needFetchDataFromNetwork(data: List<WeatherDb>): Boolean {
                return data == null || data.isEmpty()
            }

        }.parseToLiveData()
    }

}