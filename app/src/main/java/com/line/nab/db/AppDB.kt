package com.line.nab.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.line.nab.model.WeatherDb


@Database(
    entities = [
        WeatherDb::class
    ], version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun weatherDao () : WeatherDao
}