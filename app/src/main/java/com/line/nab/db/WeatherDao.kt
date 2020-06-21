package com.line.nab.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.line.nab.model.WeatherDb

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(wObj : WeatherDb)

    @Delete
    fun remove (wObj : WeatherDb)

    @Query("SELECT * FROM weather WHERE city LIKE :city ")
    fun findByCity(city: String ): LiveData<List<WeatherDb>>
}