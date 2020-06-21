package com.line.nab.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.line.nab.repository.WeatherRepository
import com.line.nab.utilities.AbsentLiveData
import com.line.nab.model.Resource
import com.line.nab.model.WeatherDb
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: WeatherRepository) : ViewModel(){
    private val name = MutableLiveData<String>()
    val result : LiveData<Resource<List<WeatherDb>>> = name.switchMap {
        if (it == null) {
            AbsentLiveData.create()
        } else {
            repository.loadWeatherByCity(it)
        }
    }

    fun getWeatherByCityName (nameOfCity : String)  {
        name.value = nameOfCity
    }
}